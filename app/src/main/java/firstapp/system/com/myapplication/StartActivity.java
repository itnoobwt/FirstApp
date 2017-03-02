package firstapp.system.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import firstapp.system.com.myapplication.activity.BaseActivity;
import org.junit.Test;

public class StartActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener
{
    @BindView(R.id.sw)
    Switch sw;
    @BindView(R.id.et)
    EditText et;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //设置主标题
        toolbar.setTitle("启动页");
//        toolbar.setSubtitle("子");
        toolbar.setSubtitleTextAppearance(this,R.style.Theme_ToolBar_Base_Title);
        toolbar.setNavigationIcon(R.mipmap.home_up_btn);
        toolbar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @OnClick(R.id.start_btn)
    public void startHome(){
//        File file = FileUtils.getInstance().getmBaseDir();
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.parse(file+"/2255.doc"),"*/*");
//        startActivity(intent);

        startActivity(new Intent(StartActivity.this,MainActivity.class));
    }
    public void onHttps(View view){
        boolean isHttps = sw.isChecked();
        if(isHttps){
            et.setText("https://123");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_fx:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Test
    public void testfile(){
        String str = "abc.DOC".toLowerCase();
        System.out.printf(str);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        toolbar.inflateMenu(R.menu.main);
        toolbar.setPopupTheme(R.style.PopupMenu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {

        return false;
    }
}
