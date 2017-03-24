package firstapp.system.com.myapplication.okhttp;

import firstapp.system.com.myapplication.MyApplication;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by user on 2017/2/6.
 */

public class RetrofitUtils
{
    private static RetrofitUtils retrofitUtils;
    private Retrofit retrofit;
    public RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.142:8080/HttpTest/")
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OKhttpClientManager.getInstance().getOkHttpClient())
                .build();
    }
    public static RetrofitUtils getInstance(){
        if(retrofitUtils == null){
            synchronized (RetrofitUtils.class){
                if(retrofitUtils == null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
