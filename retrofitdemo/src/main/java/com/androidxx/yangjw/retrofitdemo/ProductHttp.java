package com.androidxx.yangjw.retrofitdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yangjw on 2016/6/12.
 */
public interface ProductHttp {

    //http://api.liwushuo.com/v2/items?gender=1&limit=20&offset=0&generation=2
    @GET("/v2/items")
   Call<ResponseBody> loadAll(@Query("gender") String gender, @Query("limit") String limit, @Query("offset") String offset, @Query("generation") String generation);

    //http://api.liwushuo.com/v2/channels/111/items?limit=20&gender=1&offset=0&generation=2&order_by=nowhttp://api.liwushuo.com/v2/channels/111/items?limit=20&gender=1&offset=0&generation=2&order_by=now
    @GET("v2/channels/{id}/items")
    Call<ResponseBody> queryDetails(@Path("id") int id,@Query("limit") int limit,@Query("gender") int gender);
}
