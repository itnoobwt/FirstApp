package firstapp.system.com.myapplication.test;

import android.preference.PreferenceManager;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.*;
import java.security.Permission;
import java.text.SimpleDateFormat;
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

}
