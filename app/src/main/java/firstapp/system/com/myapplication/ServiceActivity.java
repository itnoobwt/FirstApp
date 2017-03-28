package firstapp.system.com.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.receive.MusicReceive;
import firstapp.system.com.myapplication.service.MusicService;
import firstapp.system.com.myapplication.utils.PreferencesUtils;

public class ServiceActivity extends BaseActivity
{
    public static final String UPDATE_ACTION = "firstapp.system.com.myapplication.service.UPDATE_ACTION";
    @BindView(R.id.play)
    ImageButton play;
    @BindView(R.id.stop)
    ImageButton stop;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.author)
    TextView author;
    private MusicReceive musicReceive;
    private Intent intentSerice;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Service使用");
        int status = PreferencesUtils.getInteger("status",0);
        setContentView(R.layout.activity_service);
        InitData(status);
    }
    public void InitData(int status){
        //注册广播
        IntentFilter intentFilter = new IntentFilter(UPDATE_ACTION);
        musicReceive = new MusicReceive(play,title,author);
        registerReceiver(musicReceive,intentFilter);
        intentSerice = new Intent(this,MusicService.class);
        startService(intentSerice);
        if(status == 1){
            play.setImageResource(R.mipmap.pause);
        }else if(status ==2){
            play.setImageResource(R.mipmap.play);
        }else if(status ==3){
            play.setImageResource(R.mipmap.play);
        }
    }
    @OnClick(R.id.play)
    public void musicPlay(){
        Intent intent = new Intent();
        intent.setAction(MusicService.CTL_ACTION);
        intent.putExtra("control",1);
        sendBroadcast(intent);
    }
    @OnClick(R.id.stop)
    public void musicStop(){
        Intent intent = new Intent();
        intent.setAction(MusicService.CTL_ACTION);
        intent.putExtra("control",2);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(musicReceive);
        stopService(intentSerice);
    }
}
