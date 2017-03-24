package firstapp.system.com.myapplication;

import android.app.Service;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import firstapp.system.com.myapplication.activity.BaseActivity;

public class AudioManagerActivity extends BaseActivity
{

    @BindView(R.id.audio_play)
    Button play;
    @BindView(R.id.audio_up)
    Button up;
    @BindView(R.id.audio_down)
    Button down;
    @BindView(R.id.audio_mute)
    ToggleButton mute;
    @BindView(R.id.audio_stop)
    Button audioStop;
    MediaPlayer mediaPlayer ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("手机音频");
        setContentView(R.layout.activity_audio_manager);
        initView();
    }

    public void initView()
    {
        //获取音频服务
        final AudioManager audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mediaPlayer = MediaPlayer.create(AudioManagerActivity.this, R.raw.earth);
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                mediaPlayer.start();
            }
        });
        audioStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mediaPlayer.stop();
            }
        });
        up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 指定调节音乐的音频，增大音量，而且显示音量图形示意
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            }
        });
        down.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 指定调节音乐的音频，降低音量，而且显示音量图形示意
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
            }
        });

        mute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                // 指定调节音乐的音频，根据isChecked确定是否需要静音
//                audioManager.setStreamMute(AudioManager.STREAM_MUSIC,
//                                        isChecked);
                audioManager.setMicrophoneMute(isChecked);
//                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_UNMUTE,AudioManager.FLAG_SHOW_UI);
            }
        });
    }


}
