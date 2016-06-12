package com.androidxx.yangjw.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .build();
        ProductHttp productHttp = retrofit.create(ProductHttp.class);


        //gender=1&limit=20&offset=0&generation=2
        final Call<ResponseBody> responseBodyCall = productHttp.loadAll("1", "20", "0", "2");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                    Log.d("androidxx", "onCreate: " + responseBodyCall.execute().body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

    }
}
