package firstapp.system.com.myapplication;

import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.widget.Toast;
import firstapp.system.com.myapplication.activity.BaseActivity;

/**
 * 震动
 */
public class VibratorActivity extends BaseActivity
{
    //需要加权限
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("Vibrator");
        setContentView(R.layout.activity_vibrator);
        vibrator = (Vibrator) getSystemService
                (Service.VIBRATOR_SERVICE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        Toast.makeText(this, "手机震动", Toast.LENGTH_SHORT).show();
        vibrator.vibrate(100);
        return super.onTouchEvent(event);
    }
}
