package firstapp.system.com.myapplication.test;

<<<<<<< HEAD
=======
import org.junit.Test;

import java.io.InputStream;
>>>>>>> 537876065acbb3a9a970c3fd52ac40a799559848
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 2017/3/29.
 */

public class DownUtil
{
    private String path;
    private String targetFile;
    private int threadNum;
    private DownTrhead[] threads;
    private int filesize;
    //http://192.168.1.142:8080/Upload/vieo/1.mov
    public DownUtil(String path,String targetFile,int threadNum){
        this.path = path;
        this.targetFile =targetFile;
        this.threadNum = threadNum;
        threads = new DownTrhead[threadNum];
    }
    public void download() throws Exception{
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept","image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
                + "application/x-shockwave-flash, application/xaml+xml, "
                + "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
                + "application/x-ms-application, application/vnd.ms-excel, "
                + "application/vnd.ms-powerpoint, application/msword, */*");
        connection.setRequestProperty("Accept-Language","zh-CN");
        connection.setRequestProperty("Charset","UTF-8");
        connection.setRequestProperty("Connection","Keep-Alive");
        connection.setRequestProperty("Range","bytes = 0-1898921");
        filesize = connection.getContentLength();
        connection.disconnect();
        System.out.println(filesize);
        int currentPartSize = filesize/threadNum;
        int yushu = filesize - currentPartSize;
        System.out.println(yushu);
        RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile,"rw");
        randomAccessFile.setLength(filesize);
        randomAccessFile.close();
//        InputStream is = connection.getInputStream();
//        int len = 0;
//        byte[] buffer = new byte[1024];
//        RandomAccessFile cuurentPart = new RandomAccessFile(targetFile,"rw");
//        while ((len = is.read(buffer))!= -1){
//            cuurentPart.write(buffer,0,len);
//        }
//        cuurentPart.close();
//        is.close();
        for (int i = 0; i < threadNum ; i++){
            int startPos = i*currentPartSize;
            RandomAccessFile cuurentPart = new RandomAccessFile(targetFile,"rw");
            //定位线程的下载位置
            cuurentPart.seek(startPos);
            System.out.println("DownUtils   "+startPos);
            threads[i] = new DownTrhead(startPos,currentPartSize,cuurentPart,path);
            threads[i].start();
        }
    }



}
class Product{

}