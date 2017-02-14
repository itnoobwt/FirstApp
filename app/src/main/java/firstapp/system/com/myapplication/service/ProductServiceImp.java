package firstapp.system.com.myapplication.service;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.TextView;
import firstapp.system.com.myapplication.entity.Product;
import firstapp.system.com.myapplication.okhttp.RetrofitUtils;
import firstapp.system.com.myapplication.utils.LogUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/2/6.
 */

public class ProductServiceImp
{
    public static String TAG = "ProductServiceImp";
    private static Retrofit retrofit;
    public static Product getProduct(final TextView textView){
        retrofit = RetrofitUtils.getInstance().getRetrofit();
        ProductService service = retrofit.create(ProductService.class);//获取API接口的实现类的实例对象
        Call<Product> call = service.getProduct();
        call.enqueue(new Callback<Product>()
        {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response)
            {
                Product product = response.body();
                textView.setText(product.toString());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t)
            {
                textView.setText(t.getMessage());
                LogUtils.e(TAG,t.getMessage()+"");
            }
        });
        return null;
    }

    /**
     * retrofit带参数的post请求
     * @param id
     * @return
     */
    public Product getProduct(String id){
        retrofit = RetrofitUtils.getInstance().getRetrofit();
        ProductService service = retrofit.create(ProductService.class);
        Call<Product> call = service.getProduct(id);
        call.enqueue(new Callback<Product>()
        {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response)
            {

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t)
            {

            }
        });
        return null;
    }

    /**
     * retrofit多个参数的post请求
     * @param id
     * @param name
     * @return
     */
    public Product getProduct(String id,String name){
        retrofit = RetrofitUtils.getInstance().getRetrofit();
        ProductService service = retrofit.create(ProductService.class);
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        Call<Product> call = service.getProduct(map);
        call.enqueue(new Callback<Product>()
        {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response)
            {

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t)
            {

            }
        });
        return null;
    }

    /**
     * 上传文件
     * @param context
     * @param fileUri
     * @return
     */
    public Product getProduct(Context context, Uri fileUri){
        retrofit = RetrofitUtils.getInstance().getRetrofit();
        ProductService service = retrofit.create(ProductService.class);
        File file = new File(fileUri.toString());
        RequestBody requestFile = RequestBody.create
                (MediaType.parse(context.getContentResolver().getType(fileUri))
                ,file);
        MultipartBody.Part body = MultipartBody.Part.createFormData
                ("pricture",file.getName(),requestFile);
        RequestBody desc = RequestBody.create(MultipartBody.FORM,
                "hello, this is description speaking");
        Call<Product> call = service.insertProduct(desc,body);
        call.enqueue(new Callback<Product>()
        {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response)
            {

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t)
            {

            }
        });
        return null;
    }

    public void downloadFile(final File filePath){
        retrofit = RetrofitUtils.getInstance().getRetrofit();
        ProductService service = retrofit.create(ProductService.class);
        Call<ResponseBody> call = service.downloadFile();
        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                InputStream is = response.body().byteStream();
                try
                {
                    FileOutputStream fos = new FileOutputStream(filePath);
                    byte[] bytes = new byte[1024];
                    int len = 0;
                    while ((len = is.read(bytes))!=-1){
                        fos.write(bytes,0,len);
                    }
                    fos.flush();
                    fos.close();
                    is.close();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {

            }
        });
    }

}
