package www.sinofreely.app.control.network;

import com.alibaba.fastjson.JSONObject;
import www.sinofreely.app.control.network.utils.NetworkClientInterf;
import www.sinofreely.app.control.network.utils.ThreadManager;

/** author it_noob
 * Created by user on 2017/4/28.
 */

public class HttpUrlConnectionClient implements NetworkClientInterf
{
    @Override
    public void get(final JSONObject jsonObject, CallBack c)
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
    public void post(final JSONObject jsonObject, CallBack c)
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
