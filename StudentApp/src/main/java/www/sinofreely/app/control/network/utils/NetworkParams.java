package www.sinofreely.app.control.network.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/** author it_noob
 * Created by user on 2017/4/28.
 *  公共变量
 */

public class NetworkParams
{
    public static String MEDIA_TYPE_JSON="application/json; charset=utf-8";
    public static String MEDIA_TYPE_PNG = "application/octet-stream; charset=utf-8";
    public static int CACHESIZE = 10*1024*1024; //缓存文件夹大小
    public static String RESULT;
    public static String ERROR = "error";
    public static int CONNECTTIMEOUT = 10000;  //设置链接超时
    public static int READTIMEOUT = 5000; //设置读取超时
    public static int WRITETIMEOUT = 5000; //设置写人超时
    public static String ENCODED_VALUE = "utf-8";
}
