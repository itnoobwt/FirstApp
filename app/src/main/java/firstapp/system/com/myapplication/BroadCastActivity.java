package firstapp.system.com.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.receive.MyReceive;

public class BroadCastActivity extends BaseActivity
{

    @BindView(R.id.bnt_send)
    Button bntSend;
    @BindView(R.id.stop_send)
    Button btn;
    MyReceive myReceive;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("广播");
        setContentView(R.layout.activity_broad_cast);
        myReceive = new MyReceive();
        IntentFilter intentFilter = new IntentFilter("code");
        registerReceiver(myReceive,intentFilter);
        bntSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent("code");
                intent.putExtra("msg","动态发送广播");
                sendBroadcast(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent("myreceive");
                intent.putExtra("msg","静态发送广播");
                sendBroadcast(intent);
            }
        });
    }



    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(myReceive); //解除广播
    }
}
