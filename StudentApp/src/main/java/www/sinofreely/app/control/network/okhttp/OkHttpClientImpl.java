package www.sinofreely.app.control.network.okhttp;

import com.alibaba.fastjson.JSONObject;
import www.sinofreely.app.control.network.utils.NetworkClientInterf;
import www.sinofreely.app.control.network.utils.ThreadManager;

/**
 * Created by user on 2017/4/28.
 */

public class OkHttpClientImpl implements NetworkClientInterf
{
    @Override
    public void get(JSONObject jsonObject, CallBack c)
    {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {

            }
        };
        ThreadManager.execute(runnable);
    }

    @Override
    public void post(JSONObject jsonObject, CallBack c)
    {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {

            }
        };
        ThreadManager.execute(runnable);
    }
}
