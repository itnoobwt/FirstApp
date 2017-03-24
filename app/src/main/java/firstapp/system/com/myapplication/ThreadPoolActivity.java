package firstapp.system.com.myapplication;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.utils.ConcurrentAsyncTask;
import firstapp.system.com.myapplication.utils.Download;
import firstapp.system.com.myapplication.utils.LogUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolActivity extends BaseActivity
{

    @BindView(R.id.pb1)
    ProgressBar pb1;
    @BindView(R.id.pb2)
    ProgressBar pb2;
    @BindView(R.id.pb3)
    ProgressBar pb3;
    @BindView(R.id.pb4)
    ProgressBar pb4;
    @BindView(R.id.btn_down)
    Button btn_down;
    @BindView(R.id.head_toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
        toolbar.setTitle("线程池");
        //        toolbar.setSubtitle("子");
        toolbar.setSubtitleTextAppearance(this,R.style.Theme_ToolBar_Base_Title);
        toolbar.setNavigationIcon(R.mipmap.home_up_btn);
        toolbar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        setSupportActionBar(toolbar);
    }
    Download download;
    @OnClick(R.id.btn_down)
    public void startThreadPool(){
        download = new Download(pb1);
        ConcurrentAsyncTask.execute(download);
        ConcurrentAsyncTask.execute(new Download(pb2));
        ConcurrentAsyncTask.execute(new Download(pb3));
    }
    @OnClick(R.id.btn_stop)
    public void stopThreadPool(){
//        download.cancel(true);
        load();
    }
    int a = 0;
    public void load(){
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        for (int i =0; i<10;i++)
        {

           Runnable runnable = new Runnable()
            {
                @Override
                public void run()
                {
                    a++;
                    LogUtils.e("thread",Thread.currentThread().getId()+"----"+a);
                }
            };
            threadPool.execute(runnable);
        }

    }
}
