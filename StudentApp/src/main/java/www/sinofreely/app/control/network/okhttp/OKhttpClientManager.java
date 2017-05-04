package www.sinofreely.app.control.network.okhttp;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import www.sinofreely.app.MyApplication;
import www.sinofreely.app.utils.FileUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static www.sinofreely.app.control.network.utils.NetworkParams.*;

/**
 * Created by user on 2017/4/28.
 */

public class OKhttpClientManager
{
    private static OKhttpClientManager okhttpClient;
    private String TAG = getClass().getName();
    private OkHttpClient okClient;
    public static OKhttpClientManager getInstance(){
        if(okhttpClient == null){
            synchronized (OKhttpClientManager.class){
                if(okhttpClient == null){
                    okhttpClient = new OKhttpClientManager();
                }
            }
        }
        return okhttpClient;
    }

    /**
     *  拦截器  设置请求头，10秒之后请求网络数据，10秒之内请求缓存数据
     */
    public Interceptor interceptor = new Interceptor()
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Request request = chain.request();
            boolean connected = MyApplication.isConnected();
            if(!connected){
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            return response;
        }
    };

    public OKhttpClientManager(){
        Cache cache = new Cache(FileUtils.getInstance().getCacheDir(),CACHESIZE);
        okClient = new OkHttpClient().newBuilder()
                .connectTimeout(CONNECTTIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READTIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(WRITETIMEOUT,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    public JSONObject okPostRequest(JSONObject jsonObject){
        RequestBody requestBody = new FormBody.Builder()
                .add("key","value")
                .build();
        Request request = new Request.Builder()
                .post(requestBody)
                .build();
        Call call = okClient.newCall(request);
        try
        {
            Response response = call.execute();
            String result = response.body().string();
            return JSONObject.parseObject(result);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return JSONObject.parseObject(ERROR);
    }
}
