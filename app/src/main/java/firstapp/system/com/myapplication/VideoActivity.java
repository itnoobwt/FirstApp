package firstapp.system.com.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firstapp.system.com.myapplication.activity.BaseActivity;
import firstapp.system.com.myapplication.okhttp.OKhttpClientManager;
import firstapp.system.com.myapplication.test.DownUtil;
import firstapp.system.com.myapplication.utils.*;

import java.io.File;
import java.util.UUID;

public class VideoActivity extends BaseActivity
{

    @BindView(R.id.video)
    VideoView video;
    @BindView(R.id.btn_video_select)
    Button btnVideoSelect;
    Uri uri;
    @BindView(R.id.btn_video_compress)
    Button btnVideoCompress;
    @BindView(R.id.btn_video_luzhi)
    Button btnVideoLuzhi;
    @BindView(R.id.btn_video_down)
    Button btnVideodown;
    @BindView(R.id.pb)
    ProgressBar pb;
    private String cmd;
    private Compressor compressor;
    private File file;
    private File file_path;
    private String TAG = "VideoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle("视频压缩");
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                video.setVideoURI(uri);
                video.start();
            }
        });
        file = FileUtils.getInstance().getVideDir();
    }
    @OnClick(R.id.btn_video_down)
    public void downVideo()
    {

//       final File file = FileUtils.getInstance().getVideDir();
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                try
//                {
//                    File f = new File(file,"1.mov");
//                    DownUtil downUtil = new DownUtil("http://192.168.1.142:8080/Upload/vieo/1.mov",
//                            f.toString(),2);
//                    downUtil.download();
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                OKhttpClientManager.getInstance().OKhttpRequestUpload("http://localhost:8080/Upload/UploadFile",
                        file_path);
            }
        }).start();


    }

    @OnClick(R.id.btn_video_luzhi)
    public void recordingVideo()
    {
        String name = UUID.randomUUID().toString() + ".mov";
        file_path = new File(file, name);
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file_path));
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
    }

    @OnClick(R.id.btn_video_select)
    public void selectVideo()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);
    }

    @OnClick(R.id.btn_video_compress)
    public void compressVideo()
    {
        compressor = new Compressor(this);
        String name = UUID.randomUUID().toString() + ".mov";
        file_path = new File(file, name);
        cmd = "-y -i " + uri + " -strict -2 -vcodec libx264 -preset ultrafast -crf 24 -acodec aac -ar 44100 -ac 2 -b:a " +
                "96k -s 640x352 -aspect 16:9 " + file_path;
        pb.setVisibility(View.VISIBLE);
        compressor.loadBinary(new InitListener()
        {
            @Override
            public void onLoadSuccess()
            {
                compressor.execCommand(cmd, new CompressListener()
                {
                    @Override
                    public void onExecSuccess(String message)
                    {
                        pb.setVisibility(View.GONE);
                        Toast.makeText(VideoActivity.this, "压缩视频成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onExecFail(String reason)
                    {
                        pb.setVisibility(View.GONE);
                        LogUtils.e(TAG,reason);
                    }

                    @Override
                    public void onExecProgress(String message)
                    {
                        LogUtils.e(TAG," - "+message);
                    }
                });
            }

            @Override
            public void onLoadFail(String reason)
            {
                LogUtils.e("VideoActivity", reason + "");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED)
        {
            return;
        }
        if (requestCode == 1)
        {
            uri = data.getData();
            video.setVideoURI(uri);
            video.start();
        }
        else if (requestCode == 2)
        {
            uri = data.getData();
            video.setVideoURI(uri);
            video.start();
        }
    }
}
