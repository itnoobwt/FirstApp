package firstapp.system.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import butterknife.OnClick;
import firstapp.system.com.myapplication.activity.BaseActivity;

public class StartActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    @OnClick(R.id.start_btn)
    public void startHome(){
        startActivity(new Intent(StartActivity.this,MainActivity.class));
    }
}
