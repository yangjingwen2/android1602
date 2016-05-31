package com.androidxx.yangjw.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "androidxx";
    private Button mGetBtn;
    private Button mAysncGetBtn;
    private Button mPostBtn;
    private Button mJsonBtn;
    OkHttpClient okHttp = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGetBtn = (Button) findViewById(R.id.okhttp_get_btn);
        mAysncGetBtn = (Button) findViewById(R.id.okhttp_get_async_btn);
        mPostBtn = (Button) findViewById(R.id.okhttp_post_sync_btn);
        mJsonBtn = (Button) findViewById(R.id.okhttp_json_sync_btn);
        mGetBtn.setOnClickListener(this);
        mAysncGetBtn.setOnClickListener(this);
        mPostBtn.setOnClickListener(this);
        mJsonBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okhttp_get_btn:
                OkHttpUtils.okGet("http://www.baidu.com");
                break;
            case R.id.okhttp_get_async_btn:
                OkHttpUtils.okAsyncGet("http://www.baidu.com", new IOkCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d(TAG, "onSuccess: " + result);
                    }
                });
                break;
            case R.id.okhttp_post_sync_btn:
                postSync();
                break;
            case R.id.okhttp_json_sync_btn:
                postJson();
                break;
        }
//

    }

    private void postJson() {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String json = "username/wangwu";
        RequestBody requestBody = RequestBody.create(mediaType,json);
        final Request request = new Request.Builder()
                .url("http://192.168.42.1:8080/WebServer/login.do")
                .post(requestBody)
                .build();
        okHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response);
            }
        });
    }

    /**
     * Post同步请求
     */
    private void postSync() {

        /**
         * 用来封装Post数据
         * 表单数据
         */
        FormBody formBody = new FormBody.Builder()
                .add("user","zhangsan")
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .url("http://192.168.42.1:8080/WebServer/login.do")
                .build();
        okHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });

    }
}
