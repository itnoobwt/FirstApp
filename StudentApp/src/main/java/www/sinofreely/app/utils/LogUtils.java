package www.sinofreely.app.utils;

import android.util.Log;

/**
 * Created by user on 2017/4/19.
 */

public class LogUtils
{
    public static boolean isLog = false;
    public static void e(String tag, String mes){
        if(isLog)
            Log.e(tag,mes);
    }
}
