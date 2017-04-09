package firstapp.system.com.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by user on 2017/3/21.
 */

public class NetWorkUtis
{
    private static NetWorkUtis netWorkUtis;
    private Context context;
    public static NetWorkUtis init(Context context){
        if(netWorkUtis == null){
            synchronized (NetWorkUtis.class){
                if(netWorkUtis == null){
                    netWorkUtis = new NetWorkUtis(context);
                }
            }
        }
        return netWorkUtis;
    }
    public NetWorkUtis(Context context){
        this.context = context;
    }
    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null){
            return networkInfo.isAvailable();
        }
        return false;
    }
}
