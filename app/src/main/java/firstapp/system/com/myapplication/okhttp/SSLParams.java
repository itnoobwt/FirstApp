package firstapp.system.com.myapplication.okhttp;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by user on 2017/2/14.
 */

public class SSLParams
{
    public SSLSocketFactory sSLSocketFactory;
    public X509TrustManager trustManager;

    public SSLSocketFactory getsSLSocketFactory()
    {
        return sSLSocketFactory;
    }

    public void setsSLSocketFactory(SSLSocketFactory sSLSocketFactory)
    {
        this.sSLSocketFactory = sSLSocketFactory;
    }

    public X509TrustManager getTrustManager()
    {
        return trustManager;
    }

    public void setTrustManager(X509TrustManager trustManager)
    {
        this.trustManager = trustManager;
    }
}
