package id.wonderdeal.wonderdealapps.utils;

import com.google.gson.JsonObject;

import id.wonderdeal.wonderdealapps.model.BaseResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by agustinaindah on 12/09/2017.
 */

public interface ApiService {

    /*-------------------------------------System-------------------------------------*/
    @GET("slider_mobile")
    Call<BaseResponse> getBanner();

    /*-------------------------------------API Product--------------------------------*/

    @GET("semua_produk")
    Call<BaseResponse> getAllProduct(@Query("page") int page);

    @GET("single_produk")
    Call<BaseResponse> getSingleProduct(@Query("id") int id);

    /*--------------------------------------------------------------------------------*/

    @GET("kategori_produk")
    Call<BaseResponse> getCategory();

    /*http://wonderdeal.id/kategori_produk_gambar*/
    @GET("kategori_produk_gambar")
    Call<BaseResponse> getCatGambar();

    @GET("produk_by_kategori")
    Call<BaseResponse> getProductByCategory(@Query("cat_id") int id);

    @GET("cari_produk")
    Call<BaseResponse> getSearch(@Query("search") String search);

    /*order_baru*/
    @POST("order_baru")
    Call<BaseResponse> postOrder(@Body JsonObject jsonRequest);

    /*http://wonderdeal.id/test_post*/
    @POST("test_post")
    Call<BaseResponse> postTest(@Body JsonObject jsonReq);

    /*wonderdeal.id/api_konfirmasi_pembayaran*/
    @POST("api_konfirmasi_pembayaran")
    Call<BaseResponse> confirmPayment(@Body JsonObject jsonRes);

}
