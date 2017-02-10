package firstapp.system.com.myapplication.service;

import firstapp.system.com.myapplication.entity.Product;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * Created by user on 2017/2/6.
 */

public interface ProductService
{
    @GET("ProductServlet")
    Call<Product> getProduct();
    /**
     * 单个参数
     * @param id
     * @return
     */
    @POST("ProductServlet")
    Call<Product> getProduct(@Query("id") String id);


    /**
     * 多个参数
     * @param map
     * @return
     */
    @POST("ProductServlet")
    Call<Product> getProduct(@QueryMap Map<String,String> map);

    /**
     * 使用Retrofit上传下载文件
     * @param file
     * @return
     */
    @Multipart
    @POST("UploadServlet")
    Call<Product> insertProduct(@Part("desc") RequestBody desc, @Part MultipartBody.Part file);

    /**
     * 下载大文件时占用内存过多，我们必须使用@Streaming注解来解决这个问题
     * @return
     */
    @Streaming
    @POST("download")
    Call<ResponseBody> downloadFile();


}
