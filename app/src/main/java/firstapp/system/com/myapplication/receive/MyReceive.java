package firstapp.system.com.myapplication.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by user on 2017/3/24.
 */

public class MyReceive extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String msg = intent.getStringExtra("msg");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
