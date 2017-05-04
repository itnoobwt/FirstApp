package firstapp.system.com.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AlertDialog;

import firstapp.system.com.myapplication.activity.BaseActivity;

/**
 * 指纹
 */
public class FingerprintActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("指纹");
        setContentView(R.layout.activity_fingerprint);
        initView();
    }

    public void initView(){
        FingerprintManagerCompat fingerprintManagerCompat = FingerprintManagerCompat.from(this);
//        FingerprintManagerCompat fingerprintManagerCompat = (FingerprintManagerCompat)
//            getSystemService(Context.FINGERPRINT_SERVICE);
        if(!fingerprintManagerCompat.isHardwareDetected()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("指纹");
            builder.setMessage("您的手机不支持，指纹识别");
            builder.setIcon(android.R.drawable.stat_sys_warning);
            builder.setCancelable(false);
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            // show this dialog.
            builder.create().show();
        }
    }
}
