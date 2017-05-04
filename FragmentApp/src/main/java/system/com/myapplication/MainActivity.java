package system.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import system.com.myapplication.activity.NewsContentActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //activity_main做了适配
        setContentView(R.layout.activity_main);
    }
    public static void actionStart(Context content, String newsTitle, String newsContent){
        Intent intent = new Intent(content,NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        content.startActivity(intent);
    }
}
