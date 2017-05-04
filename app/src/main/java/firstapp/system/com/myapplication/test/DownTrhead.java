package firstapp.system.com.myapplication.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 2017/3/29.
 */

public class DownTrhead extends Thread
{
    // 当前线程的下载位置
    private int startPos;
    // 定义当前线程负责下载的文件大小
    private int currentPartSize;
    // 当前线程需要下载的文件块
    private RandomAccessFile currentPart;
    // 定义已经该线程已下载的字节数
    public int length;
    private String url_path;
    public DownTrhead(int startPos,int currentPartSize,RandomAccessFile currentPart,String url_path){
        this.startPos = startPos;
        this.currentPartSize = currentPartSize;
        this.currentPart = currentPart;
        this.url_path = url_path;
    }

    @Override
    public void run()
    {
        super.run();
        try
        {
            URL url = new URL(url_path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            // 跳过startPos个字节，表明该线程只下载自己负责的那部分文件
            skipFully(is,this.startPos);
            byte[] buufer = new byte[1024];
            int hasRead = 0;
            // 读取网络数据，并写入本地文件
            while (length < currentPartSize && (hasRead = is.read(buufer))!=-1){
                currentPart.write(buufer,0,hasRead);
                // 累计该线程下载的总大小
                length += hasRead;
            }
            currentPart.close();
            is.close();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // 定义一个为InputStream跳过bytes字节的方法
    public static void skipFully(InputStream in, long bytes) throws IOException
    {
        long remainning = bytes;
        long len = 0;
        while (remainning > 0)
        {
            len = in.skip(remainning);
            remainning -= len;
        }
    }
}
