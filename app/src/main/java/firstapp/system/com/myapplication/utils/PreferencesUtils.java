package firstapp.system.com.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import firstapp.system.com.myapplication.MyApplication;

/**
 * Created by user on 2017/2/23.
 */

public class PreferencesUtils
{
    private static final String SHARED_PREF_NAME = "app";
    private Context ct;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static final String TAG = PreferencesUtils.class.getName();
    /**
     * 初始化
     * @param context
     */
    public static void init(Context context){
//        Context.MODE_PRIVATE：为默认操作模式,代表该文件是私有数据,只能被应用本身访问,在该模式下,写入的内容会覆盖原文件的内容
//        Context.MODE_APPEND：模式会检查文件是否存在,存在就往文件追加内容,否则就创建新文件.
//        Context.MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件.
//        MODE_WORLD_READABLE：表示当前文件可以被其他应用读取.
//        MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //TODO 待测试
    public void check(String key,Object obj){
        if(obj instanceof String){
            String str = sharedPreferences.getString(key,(String)obj);
            if(str!=null){
                LogUtils.e(TAG,"Key already exists");
                return;
            }
        }else if(obj instanceof Integer){
            Integer num = sharedPreferences.getInt(key, (Integer) obj);
        }else if(obj instanceof Boolean){
            sharedPreferences.getBoolean(key, (Boolean) obj);
        }

    }

    public static void putString(String key,String values){
        editor.putString(key,values);
        editor.commit();
    }

    public static void putInt(String key,Integer values){
        editor.putInt(key,values);
        editor.commit();
    }

    public static void putBoolean(String key,Boolean values){
        editor.putBoolean(key,values);
        editor.commit();
    }

    public static String getString(String key,String defaultValues){
        return sharedPreferences.getString(key,defaultValues);
    }
    public static Integer getInteger(String key,Integer defaultValues){
        return sharedPreferences.getInt(key,defaultValues);
    }
    public static Boolean getIBoolean(String key,Boolean defaultValues){
        return sharedPreferences.getBoolean(key,defaultValues);
    }
}
