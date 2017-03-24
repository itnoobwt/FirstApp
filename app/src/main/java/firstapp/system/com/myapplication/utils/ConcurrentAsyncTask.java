package firstapp.system.com.myapplication.utils;

import android.os.AsyncTask;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by user on 2017/3/14.
 */

public class ConcurrentAsyncTask
{
    private static ExecutorService executors = Executors.newFixedThreadPool(3);


    public static  void execute(AsyncTask<String,Integer,String> task){
        task.executeOnExecutor(executors);
//        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public static void stop(){
        executors.shutdown();
    }

}
