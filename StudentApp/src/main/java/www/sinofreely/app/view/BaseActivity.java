package www.sinofreely.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import www.sinofreely.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/4/19.
 */

public class BaseActivity extends AppCompatActivity
{
    private Unbinder unbinder;
    private String title;
    private Boolean is_back = false;
    @BindView(R.id.tool_title)
    TextView tool_title;
    @BindView(R.id.tool_title_right)
    TextView tool_title_right;
    @BindView(R.id.head_toolbar)
    Toolbar mActionBarToolbar;
    public static List<Activity> mActivitys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addActivity(this);

    }
    protected void initView(){

    }
    protected void loadData(){

    }
    @Override
    public void setContentView(@LayoutRes int layoutResID)
    {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
        getActionBarToolbar();
        initView();
        loadData();
    }

    /**
     * 设置标题
     * @param title
     */
    protected void setHeaderTitle(String title){
        this.title = title;
    }

    /**
     * 是否显示back
     * @param is_back
     */
    protected void isBack(boolean is_back){
        this.is_back = is_back;
    }
    protected Toolbar getActionBarToolbar() {
            if (mActionBarToolbar != null) {
                if(title!=null&&!title.isEmpty()){
                    if(is_back){
                        mActionBarToolbar.setNavigationIcon(R.mipmap.head_back);
                    }
                    tool_title.setText(title);
//                    mActionBarToolbar.setTitle(title);
                }
                setSupportActionBar(mActionBarToolbar);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        return mActionBarToolbar;
    }

    public void setTool_title(String str){
        tool_title.setText(str);
        if(str.equals("我的")){
            tool_title_right.setVisibility(View.VISIBLE);
            tool_title_right.setText("退出");
        }else {
            tool_title_right.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addActivity(Activity activity){
        mActivitys.add(activity);
    }

    public void removeActivity(Activity activity){
        mActivitys.remove(activity);
    }

    public void finishAll(){
        for ( Activity activity:mActivitys){
            if(activity!=null){
                activity.finish();
            }
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unbinder.unbind();//释放资源
        removeActivity(this);
    }
}
