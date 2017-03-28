package firstapp.system.com.myapplication.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import firstapp.system.com.myapplication.ServiceActivity;
import firstapp.system.com.myapplication.utils.PreferencesUtils;

import java.io.IOException;

/**
 * Created by user on 2017/3/27.
 */

public class MusicService extends Service
{
    private String[] music = new String[]{"wish.mp3","promise.mp3","beautiful.mp3"};
    public static final String CTL_ACTION = "firstapp.system.com.myapplication.service.CTL_ACTION";
    private MyReceive myReceive;
    private MediaPlayer mediaPlayer;
    private int current; //当前播放音乐索引
    private AssetManager am;
    private int status = 0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        am = getAssets();
        IntentFilter intentFilter = new IntentFilter();
        myReceive = new MyReceive();
        intentFilter.addAction(CTL_ACTION);
        registerReceiver(myReceive,intentFilter);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                current++;
                if(current >= 3){
                    current = 0;
                }
                Intent intent = new Intent();
                intent.putExtra("current",current);
                intent.setAction(ServiceActivity.UPDATE_ACTION);
                sendBroadcast(intent);
                prepareAndPlay(music[current]);
            }
        });
    }

    /**
     * 播放歌曲
     * @param muiscName
     */
    public void prepareAndPlay(String muiscName){
        try
        {
            AssetFileDescriptor afd = am.openFd(muiscName);
            mediaPlayer.reset();

            //加载资源
            mediaPlayer.setDataSource(afd.getFileDescriptor(),
                    afd.getStartOffset(),afd.getLength());
            //准备声音
            mediaPlayer.prepare();
            mediaPlayer.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    class MyReceive extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent)
        {
            int control = intent.getIntExtra("control",-1);
            switch (control){
                case 1:
                    //原来处于没有播放状态
                    if(status == 0){
                        prepareAndPlay(music[current]);
                        status = 1;
                    }else if(status == 1){//原来处于播放状态
                        mediaPlayer.pause();
                        status = 2;
                    }else if(status ==2){//原来处于暂停状态
                        mediaPlayer.start();
                        status = 1;
                    }
                    break;
                case 2:
                    mediaPlayer.stop();
                    status = 2;
                    break;
            }
            PreferencesUtils.putInt("status",status);
            Intent sendIntent = new Intent();
            sendIntent.putExtra("update",status);
            sendIntent.putExtra("current",current);
            sendIntent.setAction(ServiceActivity.UPDATE_ACTION);
            sendBroadcast(sendIntent);
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(myReceive);
    }
}
