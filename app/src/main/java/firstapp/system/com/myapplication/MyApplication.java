package firstapp.system.com.myapplication;

import android.app.Application;
import android.content.Context;
import com.facebook.drawee.backends.pipeline.Fresco;
import firstapp.system.com.myapplication.okhttp.OKhttpClientManager;
import firstapp.system.com.myapplication.utils.DialogUtils;

import java.io.IOException;

/**
 * Created by user on 2017/1/20.
 */

public class MyApplication extends Application
{
    public static Context context;
    @Override
    public void onCreate()
    {
        super.onCreate();
        Fresco.initialize(this);
        DialogUtils.init(this);
        context = getApplicationContext();
        try
        {
            OKhttpClientManager.is = (getAssets().open("server.cer"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
