package www.sinofreely.app.control.network.utils;

import com.alibaba.fastjson.JSONObject;

/** author it_noob
 * Created by user on 2017/4/28.
 */

public interface NetworkClientInterf
{
    interface CallBack<R> {
        void onSuccess(R result);
        void onFailure();
    }

    void get(final JSONObject jsonObject, CallBack c);

    void post(final JSONObject jsonObject, CallBack c);
}
