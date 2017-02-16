package firstapp.system.com.myapplication.fragment;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import firstapp.system.com.myapplication.BaseFragment;
import firstapp.system.com.myapplication.R;
import firstapp.system.com.myapplication.okhttp.MyTrustManager;
import firstapp.system.com.myapplication.okhttp.SSLParams;
import firstapp.system.com.myapplication.utils.LogUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.*;

/**
 * Created by user on 2017/1/21.
 */

/**
 * 新闻碎片
 */
public class NewsFragment extends BaseFragment
{
    @BindView(R.id.webView)
    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        bind(view);
        String[] args = new String[]{"a","b"};
        List<String> arrayList = Arrays.asList(args);
        Set<String> s = new HashSet<>(arrayList.size());
        s.addAll(arrayList);
        Iterator<String> i = s.iterator();
        Map<String,String> map = new HashMap<>(1);
        Set<Map.Entry<String,String>> a = map.entrySet();
        Set<String> set = map.keySet();
        Hashtable<String,String> hashtable = new Hashtable<>(map);
        hashtable.entrySet();  //
        map.keySet();
        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
            {
                super.onReceivedSslError(view, handler, error);

                handler.proceed();
            }
        });
        webView.loadUrl("https://wx.qq.com");
        return view;
    }

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
}
