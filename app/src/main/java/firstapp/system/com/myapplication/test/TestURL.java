package firstapp.system.com.myapplication.test;

import android.preference.PreferenceManager;
import firstapp.system.com.myapplication.okhttp.OKhttpClientManager;
import org.junit.Test;

import java.io.*;
import java.net.*;
import java.security.Permission;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * Created by user on 2017/3/20.
 */

public class TestURL
{
    @Test
    public void getNetWork(){
        try
        {
            URL url = new URL("http://192.168.1.142:8080/HttpTest/ProductServlet");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println(connection.getResponseMessage());
            System.out.println( connection.getRequestMethod());
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
    @Test
    public void UDPSocket(){

        try
        {
            DatagramSocket datagramSocket = new DatagramSocket();
            //设置10秒无响应就会超时
            datagramSocket.setSoTimeout(10000);
            //建立数据包
            InetAddress address = InetAddress.getByName("time.nist.gov");
            DatagramPacket request = new DatagramPacket(new byte[1],1,address,13);

            DatagramPacket response = new DatagramPacket(new byte[1024],new byte[1024].length);
            datagramSocket.send(request);
            datagramSocket.receive(response);

            String s = new String(response.getData(),0,response.getLength(),"US-ASCII");
            System.out.println(s);
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @Test
    public void test1(){

//        String str = "http://localhost:8080/Upload/vieo/1.mov";
//
//        DownUtil downUtil = new DownUtil(str,"F://1.mov",2);
//        try
//        {
//            downUtil.download();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }

//        List<String> list = new ArrayList<>();
//        for (int i = 0 ; i < 3 ; i++){
//            list.add(""+i);
//        }
//        List<String> list1 = new ArrayList<>();
//        list1.addAll(list);
//        list.clear();
//        System.out.println(list1.size()+"");
//        List<String> list = new ArrayList<>();
//        for (int i = 0 ; i < 3 ; i++){
//            list.add(""+i);
//        }
//        List<String> list1 = list;
//        list.clear();
//        System.out.println(list1.size()+"");
        String s = "123";
        s = "qqq";
        String a = s;
        System.out.println(a+"   "+s);
        try
        {
            String as = new String(s.getBytes(),"UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

}
