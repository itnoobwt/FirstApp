package firstapp.system.com.myapplication;

import android.app.Application;
import android.content.Context;
import com.facebook.drawee.backends.pipeline.Fresco;
import firstapp.system.com.myapplication.utils.DialogUtils;

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
    }
}
