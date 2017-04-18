package system.com.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.gson.Gson;
import system.com.app.presenter.DaggerMainComponent;
import system.com.app.presenter.Poetry;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
{
    //添加@Inject注解，表示这个mPoetry是需要注入的
    @Inject
    Poetry mPoetry;
    @Inject
    Gson mGson;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainComponent.builder().build().inject(this);
        initView();
    }

    private void initView(){
        mTextView = (TextView) findViewById(R.id.tv);
        String json = mGson.toJson(mPoetry);
        mTextView.setText(json);
    }
}

