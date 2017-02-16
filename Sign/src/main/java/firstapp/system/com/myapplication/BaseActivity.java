package firstapp.system.com.myapplication;

import android.database.ContentObservable;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/2/15.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    private List<ContentObserver> observers = new ArrayList<ContentObserver>();

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            onHander(msg);
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        InitData();
        InitView();
    }
    public abstract void InitData();

    public abstract void InitView();

    public abstract void onHander(Message message);
    /**
     * 注册任务监听
     * @param observer
     */
    public void registerObserver(ContentObserver observer){
        observers.add(observer);
    }

    /**
     * 取消任务监听
     * @param observer
     */
    public void unregisterObserver(ContentObserver observer){
        observers.remove(observer);
    }
}
