package firstapp.system.com.myapplication.okhttp;

import android.content.Context;
import firstapp.system.com.myapplication.utils.FileUtils;
import firstapp.system.com.myapplication.utils.LogUtils;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
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
    private Context context;
    private InputStream is;

    public static OKhttpClientManager getInstance(Context context){
        if(okhttpClient == null){
            synchronized (OKhttpClientManager.class){
                if(okhttpClient == null){
                    okhttpClient = new OKhttpClientManager(context);
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
            response.newBuilder().addHeader("Cache-Control",String.format("max-age=%d",10000));
            return null;
        }
    };

    /**
     * 设置网络请求参数
     */
    public OKhttpClientManager(Context context){
        Cache cache = new Cache(FileUtils.getInstance().getCacheDir(),CACHESIZE);
        try
        {
            is = context.getAssets().open("selfcert.cer");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        SSLSocketFactory socketFactory  = setCertificates(is);

        okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .socketFactory(socketFactory)
                .hostnameVerifier(new HostnameVerifier()
                {
                    @Override
                    public boolean verify(String hostname, SSLSession session)
                    {
                        return true;
                    }
                })
                .cache(cache)
                .build();
    }

    public OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

    public SSLSocketFactory setCertificates(InputStream inputStream){
        try
        {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            String certificateAlias = Integer.toBinaryString(index++);
            Certificate certificate = factory.generateCertificate(inputStream);
            LogUtils.e("ca","ca = "+((X509Certificate)certificate).getPublicKey());
            LogUtils.e("ca","key = "+((X509Certificate)certificate).getSubjectDN());
            keyStore.setCertificateEntry(certificateAlias,certificate);
            try
            {
                inputStream.close();
            }catch (IOException e){

            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory
                    .getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            try
            {
                sslContext.init(null,trustManagerFactory.getTrustManagers(),new SecureRandom());
            }
            catch (KeyManagementException e)
            {
                e.printStackTrace();
            }
            return sslContext.getSocketFactory();
        }
        catch (CertificateException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (KeyStoreException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public String OKhttpRequest(String url){
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        try
        {
            Response response = call.execute();
            return result = response.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            LogUtils.e("OKHTTP",e.getMessage()+"");
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
            HttpURLConnection connection = (HttpURLConnection)new URL("https://192.168.1.144:8443/power/").openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())) ;
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line=reader.readLine())!=null){
                buffer.append(line);
            }
            LogUtils.e("success",buffer.toString());
            return buffer.toString();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * HttpsURLConnection get请求
     * @param inputStream
     * @return
     */
    public String getHttps(InputStream inputStream){
        try
        {
            SSLSocketFactory sc = setCertificates(inputStream);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc);
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
            {
                @Override
                public boolean verify(String hostname, SSLSession session)
                {
                    return true;
                }
            });
            URL url = new URL("https://192.168.1.144:8443/power/");
            HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(https.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line=reader.readLine())!=null){
                buffer.append(line);
            }
            LogUtils.e("success",buffer.toString());
            return buffer.toString();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
