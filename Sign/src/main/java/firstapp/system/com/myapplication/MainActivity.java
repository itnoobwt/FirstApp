package firstapp.system.com.myapplication;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.security.AccessControlException;
import java.security.MessageDigest;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
{
    public ContentObserver observer;
    public static final String APP_PRIVATE_HEADER_TEXT="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALUm1bBSCBWG1BzXnOMSQ8Mi24yDD9twjF3P8IAA+X+zhgsg9joJM0ETdSHSCV2ZWkyPD/uK3/G2+cG8ch7sRg4+7tYTlAc1W3t4Z4A1CxnjTPAGheZj71soFpOeJdHsaahaoibShLVjRSKmlm1Iwb9RubHRz2wFG+TsU9DUp7/lAgMBAAECgYB6ewpqT9yFaYcLBUFfSl8klfHpvEfBcASH/Ws3sPEAxcnwxlWVAdl0u4or1s5cSa6DKwt8YPZvgO63bgmfHNNz9QOE62V6ZDmT0VIJh2Qe1ZH7qKS9Uyq/qL3KF5szPbIMZ2/8Ska9Covf5ByWZ6XLkWvl4fQJJarx3+RowXuXoQJBAN398JRz/Ribkr5q6Lhix90HBRn5uImUwtIArdmytvoTH9SUzcNccr3xWfbIGz//hKkKtRNYJljH9M+QzSUnPJ0CQQDQ5zm/hGSrC74G1/PjKUT8oEG4IJ3dW5EQxyRGpulfL/UWtvMBs6AcLekwrykE/CVSadwNBV2L/1BjEgh/q1npAkEA0liz5Mb/FJkddCbzOvwGq63iLJnOPTdjXv/bu8M21EDY0WLqkeoAo3CVMVytTXf9qPuHVrwZIdjeZOutJz5FlQJAUcRvwhrcHGc4Ng2JFL0ul4NStfNuVxpkKaEI/e6PIgbvc7igXWYloWIHCFtHPwBCy4NL8nzLJOUGXgDM+svjIQJBANDS6EmRU+luejpYi6hxT1hOw6K0ZHP04camesACBF+tantivox6MFtlKVnTd4ZPypZyyfYB0Jvam4BAIUqCHoM=";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAPPCerts();


    }

    @Override
    public void InitData()
    {
        observer = new ContentObserver(handler)
        {
            @Override
            public void onChange(boolean selfChange)
            {
                super.onChange(selfChange);
                handler.sendEmptyMessage(1);
            }
        };
        registerObserver(observer);
    }

    @Override
    public void InitView()
    {

    }

    @Override
    public void onHander(Message message)
    {
        switch (message.what){

        }
    }


    public void getAPPCerts(){
        String pack = getPackageName();
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> plist = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS
                | PackageManager.GET_GIDS
                | PackageManager.GET_SIGNATURES);

        ArrayList<Certificate> list = new ArrayList<Certificate>();
        PackageInfo packageInfo = null;
        for (PackageInfo info :plist){
            if(pack.equals(info.packageName)){
                packageInfo = info;
            }
        }
        for (Signature signature : packageInfo.signatures){
            list.add(decodeCertificate(signature.toByteArray()));
        }
        Certificate[] certificates = list.toArray(new Certificate[list.size()]);
        try
        {
            byte[] bytes = getAppCertHash(certificates[0]);
            String key = ByteArrayToString(bytes,0);
            String value = headerJiami(key,APP_PRIVATE_HEADER_TEXT);
            Log.e("key",value);
        }
        catch (CertificateEncodingException e)
        {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public Certificate decodeCertificate(byte[] certData)
    {
        X509Certificate cert = null;
        try
        {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(certData));
        }catch (CertificateException e){
            e.printStackTrace();
        }

        return cert;
    }

    public static byte[] getAppCertHash(Certificate appCert) throws CertificateEncodingException
    {
        /**
         * Note: This loop is needed as workaround for a bug in Android 2.3.
         * After a failed certificate verification in a previous step the
         * MessageDigest.getInstance("SHA") call will fail with the
         * AlgorithmNotSupported exception. But a second try will normally
         * succeed.
         */
        MessageDigest md = null;
        for (int i = 0; i < 10; i++) {
            try {
                md = MessageDigest.getInstance("SHA1");
                break;
            } catch (Exception e) {
            }
        }
        if (md == null) {
            throw new AccessControlException("Hash can not be computed");
        }
        return md.digest(appCert.getEncoded());
    }

    public static String ByteArrayToString(byte[] b, int start) {
        StringBuffer s = new StringBuffer();
        for (int i = start; i < b.length; i++) {
            s.append(Integer.toHexString(0x100 + (b[i] & 0xff)).substring(1));
        }
        return s.toString();
    }

    public String headerJiemi(String key) throws UnsupportedEncodingException
    {
        try {
//            byte[] bytes = android.util.Base64.decode(APP_PRIVATE_HEADER_KEY, 0);
//            DES3Model des3Model = new DES3Model();
//            @SuppressWarnings("static-access")
//            byte[] b = des3Model.decrypt(bytes, key.getBytes());
//            String s2 = new String(b, "UTF-8");
//            return s2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String headerJiami(String key,String text) throws UnsupportedEncodingException
    {
        try {

            byte[] b = DES3Model.encrypt(text.getBytes(),key.getBytes());

            String s2 = new String(android.util.Base64.encode(b,0), "UTF-8");
            return s2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
