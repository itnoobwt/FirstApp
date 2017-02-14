package firstapp.system.com.myapplication.okhttp;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by user on 2017/2/14.
 */

public class MyTrustManager implements X509TrustManager
{
    private X509TrustManager defaultTrustManager;
    private X509TrustManager localTrustManager;
    public MyTrustManager(){}
    public MyTrustManager(X509TrustManager localTrustManager) throws NoSuchAlgorithmException, KeyStoreException
    {
        TrustManagerFactory var4 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        var4.init((KeyStore) null);
        defaultTrustManager = chooseTrustManager(var4.getTrustManagers());
        this.localTrustManager = localTrustManager;
    }


    public X509TrustManager chooseTrustManager(TrustManager[] trustManagers){
        for (TrustManager manager : trustManagers){
            if(manager instanceof X509TrustManager){
                return  (X509TrustManager)manager;
            }
        }
        return null;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
    {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
    {
        defaultTrustManager.checkClientTrusted(chain,authType);
    }

    @Override
    public X509Certificate[] getAcceptedIssuers()
    {
        return new X509Certificate[0];
    }
}
