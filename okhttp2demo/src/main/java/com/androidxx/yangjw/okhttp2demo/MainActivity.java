package com.androidxx.yangjw.okhttp2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidxx.yangjw.httplibrary.IOkTaskCallback;
import com.androidxx.yangjw.httplibrary.OkHttpTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements IOkTaskCallback{

    private static final String TAG = "androidxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.ok_get_btn:
                OkHttpTask.newInstance(this).enqueue(this).start("http://www.baidu.com");
                OkHttpTask.newInstance(this).enqueue(this).start("http://www.baidu.com");
                break;
            case R.id.ok_post_btn:
                HashMap<String,String> map = new HashMap<>();
                map.put("user","android1602");
                OkHttpTask.newInstance(this).post(map, OkHttpTask.OkHttpTaskBuidler.OkType.json).enqueue(this).start("http://192.168.42.1:8080/WebServer/login.do");
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        Log.d(TAG, "onSuccess:-- " + result);
    }
}
