package www.sinofreely.app.control.network.utils;

import www.sinofreely.app.control.network.HttpUrlConnectionClient;
import www.sinofreely.app.control.network.okhttp.OkHttpClientImpl;
import www.sinofreely.app.control.network.retrofit.RetrofitClient;
import www.sinofreely.app.control.network.volley.VolleyClient;

/** author it_noob
 * Created by user on 2017/4/28.
 */

public class NetworkClientFactory
{
    public static NetworkClientInterf newClient(NetworkClientType networkClientType) {
        switch (networkClientType) {
            case HttpUrlConnetion:
                return new HttpUrlConnectionClient();

            case Volley:
                return new VolleyClient();

            case Retrofit:
                return new RetrofitClient();

            case OkHttp:
                return new OkHttpClientImpl();

            default:
                return new HttpUrlConnectionClient();
        }
    }
}
