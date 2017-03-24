package firstapp.system.com.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.service.DownloadServeice;
import firstapp.system.com.myapplication.service.aidl.IMyAidlInterface;
import firstapp.system.com.myapplication.utils.LogUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StartActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener
{
    @BindView(R.id.sw)
    Switch sw;
    @BindView(R.id.et)
    EditText et;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.binder_btn)
    Button binder_btn;
    @BindView(R.id.btn_down)
    Button btn_down;

    private static final String TAG = "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("启动页");
        setContentView(R.layout.activity_start);
        //设置主标题
//        toolbar.setSubtitle("子");
//        toolbar.setSubtitleTextAppearance(this,R.style.Theme_ToolBar_Base_Title);
//        toolbar.setNavigationIcon(R.mipmap.home_up_btn);
//        toolbar.setPopupTheme(R.style.PopupMenu);
//        toolbar.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//            }
//        });
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @OnClick(R.id.start_btn)
    public void startHome(){
//        File file = FileUtils.getInstance().getmBaseDir();
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.parse(file+"/2255.doc"),"*/*");
//        startActivity(intent);

        startActivity(new Intent(StartActivity.this,MainActivity.class));
    }

    ServiceConnection connection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            IMyAidlInterface binder = (IMyAidlInterface)service;
            try
            {
               int count = binder.plus(1,2);
               String str = binder.toUpperCase("asdfg");
                LogUtils.e(TAG,count+"----"+str);
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            LogUtils.e("",""+name);
        }
    };
    @OnClick(R.id.binder_btn)
    public void start(){

        Intent intent = new Intent(this, DownloadServeice.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }
    @OnClick(R.id.btnStart)
    public void startService(){
        LogUtils.e(TAG,"startService  "+ Process.myPid());
        Intent intent = new Intent(this, DownloadServeice.class);
        startService(intent);
    }
    @OnClick(R.id.btnStop)
    public void stopService(){
        Intent intent = new Intent(this, DownloadServeice.class);
        stopService(intent);
    }
    @OnClick(R.id.un_btn)
    public void unService(){
        unbindService(connection);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_fx:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Test
    public void testfile(){
        String str = "abc.DOC".toLowerCase();
        System.out.printf(str);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
//        mActionBarToolbar.inflateMenu(R.menu.main);
//        mActionBarToolbar.setPopupTheme(R.style.PopupMenu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {

        return false;
    }


    @OnClick(R.id.btn_down)
    public void startThreadPool(){
        startActivity(new Intent(this,ThreadPoolActivity.class));
    }
    @OnClick(R.id.btn_http)
    public void http(){

    }
}
