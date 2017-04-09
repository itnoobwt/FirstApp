package firstapp.system.com.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.INotificationSideChannel;
import firstapp.system.com.myapplication.service.aidl.IMyAidlInterface;
import firstapp.system.com.myapplication.utils.LogUtils;

import java.util.Calendar;

/**
 * Created by user on 2017/3/3.
 */

public class DownloadServeice extends Service
{
    private static String TAG = "DownloadServeice";
    public class DownloadBinder extends Binder{
        public void startDownload(){
            LogUtils.e(TAG,"开始下载");
        }
    }
    IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub()
    {
        @Override
        public int plus(int a, int b) throws RemoteException
        {
            return a+b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException
        {
            if(str!=null){
                return str.toUpperCase();
            }
            return null;
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return stub;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        try
        {
//            LogUtils.e(TAG, Calendar.getInstance().get(Calendar.HOUR)+
//                    "时"+Calendar.getInstance().get(Calendar.MINUTE)+"分"
//                    +Calendar.getInstance().get(Calendar.SECOND)+"--"+ Process.myPid());
            Thread.sleep(30000);//5分钟
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        LogUtils.e(TAG, Calendar.getInstance().get(Calendar.HOUR)+
                "时"+Calendar.getInstance().get(Calendar.MINUTE)+"分"
                +Calendar.getInstance().get(Calendar.SECOND)+"--"+ Process.myPid());
        LogUtils.e(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        LogUtils.e(TAG,"onStartCommand  "+Process.myPid());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        LogUtils.e(TAG,"onDestroy");
    }
}
