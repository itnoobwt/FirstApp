package firstapp.system.com.myapplication.service;

import firstapp.system.com.myapplication.entity.Product;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 2017/2/6.
 */

public interface ProductService
{
    @GET("/ProductService")
    Call<Product> getProduct();
}
