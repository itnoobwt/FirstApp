package firstapp.system.com.myapplication.utils;

import android.content.Context;
import android.view.View;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import firstapp.system.com.myapplication.R;

/**
 * Created by user on 2017/1/21.
 */

public class DialogUtils
{
    private static DialogUtils dialogUtils;
    public DialogUtils(){}
    public static Context context;
    public static void init(Context ct){
        context = ct;
    }

    public static DialogUtils getInstance(){
        if(dialogUtils == null){
            synchronized (DialogUtils.class){
                if(dialogUtils == null){
                    dialogUtils = new DialogUtils();
                }
            }
        }
        return dialogUtils;
    }

    /**
     *
     * @param title 标题
     * @param mes   内容
     */
    public void showDialog(String title,String mes){
        final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(context);
        dialogBuilder
                .withTitle(title)  //设置标题
                .withTitleColor("#FFFFFF")         //设置背景
                .withDividerColor("#11000000")                              //def
                .withMessage(mes)     //设置内容
                .withMessageColor("#FFFFFFFF")              //设置内容背景
                .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)                               //def
                .withIcon(context.getDrawable(R.mipmap.icon))
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
