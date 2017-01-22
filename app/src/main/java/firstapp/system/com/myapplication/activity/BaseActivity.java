package firstapp.system.com.myapplication.activity;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import de.greenrobot.event.EventBus;
import firstapp.system.com.myapplication.R;

/**
 * Created by user on 2017/1/20.
 */

public class BaseActivity extends AppCompatActivity
{
    private Unbinder unbinder;
    @Override
    public void setContentView(@LayoutRes int layoutResID)
    {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unbinder.unbind(); //释放资源
        EventBus.getDefault().unregister(this);
    }

    /**
     * 消息框
     */
    protected void showDialog(){
        final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(this);
        dialogBuilder
                .withTitle("Modal Dialog")  //设置标题
                .withTitleColor("#FFFFFF")         //设置背景
                .withDividerColor("#11000000")                              //def
                .withMessage("This is a modal Dialog.")     //设置内容
                .withMessageColor("#FFFFFFFF")              //设置内容背景
                .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)                               //def
                .withIcon(getDrawable(R.mipmap.icon))
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .withDuration(700)                                          //def
                .withEffect(Effectstype.Shake)      //启动dialog模式
                .withButton1Text("OK")
                .withButton2Text("Cancel")
//                .setCustomView(R.layout.custom_view,this)           //添加布局
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .show();
    }
}
