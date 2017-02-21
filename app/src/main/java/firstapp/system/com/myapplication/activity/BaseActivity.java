package firstapp.system.com.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import firstapp.system.com.myapplication.R;
import firstapp.system.com.myapplication.utils.FileUtils;

import java.io.File;
import java.util.UUID;

/**
 * Created by user on 2017/1/20.
 */

public class BaseActivity extends AppCompatActivity
{
    private Unbinder unbinder;
    public Uri uri;
    public static final int CAMERA_NUM = 2;  //相机
    public static final int PHOTO_NUM = 3;  //相册
    public static final int CUT_NUM = 4;
    @Override
    public void setContentView(@LayoutRes int layoutResID)
    {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unbinder.unbind(); //释放资源
    }

    public void requestPermission(){
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permission == PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
                Toast.makeText(this, "您拒绝了调用相机权限", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
            }
        }
    }



    /**
     * 消息框
     */
    protected void showDialog(String title,String mes){
        final NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(this);
        dialogBuilder
                .withTitle(title)  //设置标题
                .withTitleColor("#FFFFFF")         //设置背景
                .withDividerColor("#11000000")                              //def
                .withMessage(mes)     //设置内容
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

    public void camera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        uri = Uri.fromFile(new File(FileUtils.getInstance().getImageDir(), getName()));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", true);// false回调是data为null，true回调不为null
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //        intent.putExtra("crop", true);  //发送裁剪信号
        //        intent.putExtra("aspectX", 1);  //X方向上的比例
        //        intent.putExtra("aspectY", 1);  //Y方向上的比例
        //        intent.putExtra("outputX", 1000); //裁剪区的宽
        //        intent.putExtra("outputY", 1000);   //裁剪区的高
        //        intent.putExtra("scale", true);  //是否保留比例
        //        intent.putExtra("noFaceDetection",true); //是否取消人脸识别功能
        //file:///storage/emulated/0/CaseAndroid/camera/51a6035c-a6ee-425f-88f6-a201faa7ee65.jpg
        startActivityForResult(intent, CAMERA_NUM);
    }

    public String getName(){
        return UUID.randomUUID().toString() + ".jpg";
    }

    /**
     * 调用裁剪
     * @param uri
     */
    public void cropIma(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("return-data", true);// false回调是data为null，true回调不为null
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("crop", "circle");  //发送裁剪信号   类型：circle(圆形)
        intent.putExtra("aspectX", 1);  //X方向上的比例
        intent.putExtra("aspectY", 1);  //Y方向上的比例
        intent.putExtra("outputX", 300); //裁剪区的宽
        intent.putExtra("outputY", 300);   //裁剪区的高
        intent.putExtra("scale", true);  //是否保留比例
        intent.putExtra("noFaceDetection",true); //是否取消人脸识别功能
        //file:///storage/emulated/0/CaseAndroid/camera/51a6035c-a6ee-425f-88f6-a201faa7ee65.jpg
        startActivityForResult(intent, CUT_NUM);
    }

    /**
     * 相册
     */
    public void photo(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");  //选择内容为图片
        intent.putExtra("return-data",false);
        startActivityForResult(intent, 3);
    }
}
