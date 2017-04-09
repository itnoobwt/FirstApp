package firstapp.system.com.myapplication;

import android.app.Application;
import android.content.Context;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import firstapp.system.com.myapplication.okhttp.OKhttpClientManager;
import firstapp.system.com.myapplication.utils.DialogUtils;
import firstapp.system.com.myapplication.utils.NetWorkUtis;

import java.io.IOException;

/**
 * Created by user on 2017/1/20.
 */

public class MyApplication extends Application
{
    public static NetWorkUtis netWorkUtis;
    @Override
    public void onCreate()
    {
        super.onCreate();
        Fresco.initialize(this);
        DialogUtils.init(this);
        netWorkUtis = NetWorkUtis.init(getApplicationContext());
        try
        {
            OKhttpClientManager.is = (getAssets().open("wt_server.cer"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
