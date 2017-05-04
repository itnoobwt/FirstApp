package www.sinofreely.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import www.sinofreely.app.utils.LogUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 2017/4/19.
 */

public class MyApplication extends Application
{
    public static Context appContext;
    @Override
    public void onCreate()
    {

        super.onCreate();
        //日志输出
        LogUtils.isLog = true;
        //图片初始化
        Fresco.initialize(this);
        appContext = getApplicationContext();
//        Set<RequestListener> requestListeners = new HashSet<>();
//        requestListeners.add(new RequestLoggingListener());
//        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
//                // other setters
//                .setRequestListeners(requestListeners)
//                .build();
//        Fresco.initialize(this, config);
//        FLog.setMinimumLoggingLevel(FLog.ERROR);

    }

    /**
     * 检查网络
     * @return
     */
    public static boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null){
            return networkInfo.isAvailable();
        }
        return false;
    }

}
