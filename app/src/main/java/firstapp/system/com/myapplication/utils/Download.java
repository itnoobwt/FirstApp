package firstapp.system.com.myapplication.utils;

import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * Created by user on 2017/3/14.
 */

public class Download extends AsyncTask<String,Integer,String>
{
    private int index;
    private ProgressBar progressBar;
    private static String TAG = "Download";
    public Download(ProgressBar progressBar){
        this.progressBar = progressBar;
    }
    @Override
    protected String doInBackground(String... params)
    {
        for (int i = 0;i<101;i++){
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            publishProgress(i);
        }

        return "开始下载"+index;
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
        LogUtils.e(TAG,values[0]+"");
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        LogUtils.e(TAG,s);
    }

    @Override
    protected void onCancelled(String s)
    {
        super.onCancelled(s);
    }
}
