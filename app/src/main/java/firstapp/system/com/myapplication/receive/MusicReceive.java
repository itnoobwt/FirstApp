package firstapp.system.com.myapplication.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.TextView;
import firstapp.system.com.myapplication.R;

/**
 * Created by user on 2017/3/27.
 */

public class MusicReceive extends BroadcastReceiver
{
    private String[] titleStr = new String[]{"心愿","约定","美丽新世界"};
    private String[] authorStr = new String[]{"未知艺术家","周蕙","伍佰"};
    private ImageButton play;
    private TextView title,author;
    public MusicReceive(ImageButton play,TextView title,TextView author){
        this.play = play;
        this.title = title;
        this.author = author;
    }
    @Override
    public void onReceive(Context context, Intent intent)
    {
        int update = intent.getIntExtra("update",-1);
        int current = intent.getIntExtra("current",-1);
        if(current>=0){
            title.setText(titleStr[current]);
            author.setText(authorStr[current]);
        }
        switch (update){
            case 1:
                play.setImageResource(R.mipmap.pause);
                break;
            case 2:
                play.setImageResource(R.mipmap.play);
                break;
            case 3:
                play.setImageResource(R.mipmap.play);
                break;
        }
    }
}
