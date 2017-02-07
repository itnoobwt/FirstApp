package firstapp.system.com.myapplication.service;

import android.widget.TextView;
import firstapp.system.com.myapplication.entity.Product;
import firstapp.system.com.myapplication.okhttp.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 2017/2/6.
 */

public class ProductServiceImp
{
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
            }
        });
        return null;
    }
}
