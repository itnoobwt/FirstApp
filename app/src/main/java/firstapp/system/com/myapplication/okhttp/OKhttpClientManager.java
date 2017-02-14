package firstapp.system.com.myapplication.okhttp;

import firstapp.system.com.myapplication.utils.FileUtils;
import firstapp.system.com.myapplication.utils.LogUtils;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2017/1/12.
 */

public class OKhttpClientManager
{
    private String MEDIA_TYPE_JSON="application/json; charset=utf-8";
    private String MEDIA_TYPE_PNG = "application/octet-stream; charset=utf-8";
    private static OKhttpClientManager okhttpClient;
    private int CACHESIZE = 10*1024*1024; //缓存文件夹大小
    private OkHttpClient okHttpClient;
    private String result;
    private String ERROR = "error";
    public static InputStream is;
    private String TAG = "OKhttpClientManager";
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
            Response response = chain.proceed(chain.request());
            response.newBuilder().header("Cache-Control",String.format("max-age=%d",10000)).build();
            return response;
        }
    };

    /**
     * 设置网络请求参数
     */
    public OKhttpClientManager(){
        Cache cache = new Cache(FileUtils.getInstance().getCacheDir(),CACHESIZE);
        SSLParams sslParams = getSslSocketFactory(is);
        //okhttp3 日志拦截器有四个级别(NONE、BASIC、HEADERS、BODY)
        //Basic 日志会输出请求类型(request type),请求地址(url),请求大小(size of request body),响应码(response status)响应大小(size of response  body)
        //headers 输出请求和响应的头信息(headers)，请求类型(request type)，请求地址(request url)，响应码(response status)。
        //Body 输出请求和响应的头信息(headers)和内容(body)。
        //BuildConfig.DEBUG 开发环境返回true，对于您的生产环境返回false
//        if(BuildConfig.DEBUG)
//        {
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }
        okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .hostnameVerifier(new  HostnameVerifier()
                {
                    @Override
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.getsSLSocketFactory(),sslParams.getTrustManager())
                .cache(cache)
                .build();
    }
    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }


    /**
     * https
     * @param inputStream
     * @return
     */
    public SSLParams getSslSocketFactory(InputStream inputStream){
        SSLParams sslParams = new SSLParams();
        TrustManager[] trustManagers = prepareTrustManager(inputStream);
        try
        {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = null;
            if(trustManagers!=null){
                trustManager = new MyTrustManager(new MyTrustManager().chooseTrustManager(trustManagers));
            }
            sslContext.init(null,trustManagers,null);
            sslParams.setsSLSocketFactory(sslContext.getSocketFactory());
            sslParams.setTrustManager(trustManager);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (KeyStoreException e)
        {
            e.printStackTrace();
        }
        catch (KeyManagementException e)
        {
            e.printStackTrace();
        }
        return sslParams;
    }

    /**
     * https
     * @param inputStream
     * @return
     */
    public TrustManager[] prepareTrustManager(InputStream inputStream){
        TrustManager[] trustManagers = null;
        try
        {
            CertificateFactory certificateFactory =CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            keyStore.setCertificateEntry("server",certificateFactory.generateCertificate(inputStream));
            if(inputStream!=null)
                inputStream.close();
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            trustManagers = trustManagerFactory.getTrustManagers();
        }
        catch (CertificateException e)
        {
            e.printStackTrace();
        }
        catch (KeyStoreException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return  trustManagers;
    }


    public String OKhttpRequest(String url){
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        try
        {
            Response response = call.execute();
            return result = response.body().string();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            LogUtils.e(TAG,e.getMessage()+"");
        }
        return ERROR;
    }

    /**
     * post请求
     * @param url
     * @param json
     * @return
     */
    public String OKhttpRequest(String url,String json){
        RequestBody requestBody = new FormBody.Builder()
                .add("key","value")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);

        return "";
    }

    /**
     * 上传图片
     * @param url
     * @param file
     * @return
     */
    public String OKhttpRequestUpload(String url, File file){
        RequestBody requestBody = RequestBody.create(MediaType.parse(MEDIA_TYPE_PNG),file);
        RequestBody body = new MultipartBody.Builder()
                .addFormDataPart("名称","文件名称",requestBody)
                .addFormDataPart("key","value")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        return "";
    }




    public String getHttp(){
        try
        {
            HttpURLConnection connection = (HttpURLConnection)new URL("https://192.168.1.137:8443/MyHttps/ProductServlet").openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())) ;
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line=reader.readLine())!=null){
                buffer.append(line);
            }
            LogUtils.e(TAG,buffer.toString());
            return buffer.toString();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            LogUtils.e(TAG,e.getMessage()+"");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            LogUtils.e(TAG,e.getMessage()+"");
        }
        return "";
    }

    /**
     * HttpsURLConnection get请求
     * @param inputStream
     * @return
     */
    public String getHttps(InputStream inputStream){
//        try
//        {
//            SSLSocketFactory sc = getSslSocketFactory(inputStream);
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc);
//            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
//            {
//                @Override
//                public boolean verify(String hostname, SSLSession session)
//                {
//                    return true;
//                }
//            });
//            URL url = new URL("https://192.168.1.137:8443/MyHttps/ProductServlet");
//            HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(https.getInputStream()));
//            StringBuffer buffer = new StringBuffer();
//            String line;
//            while ((line=reader.readLine())!=null){
//                buffer.append(line);
//            }
//            LogUtils.e(TAG,buffer.toString());
//            return buffer.toString();
//        }
//        catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        return "";
    }
}
