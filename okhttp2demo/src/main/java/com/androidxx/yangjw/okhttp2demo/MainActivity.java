package com.androidxx.yangjw.okhttp2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidxx.yangjw.httplibrary.IOkTaskCallback;
import com.androidxx.yangjw.httplibrary.OkHttpTask;

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
                OkHttpTask.newInstance().enqueue(this).start("eeeeeeeee");
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        Log.d(TAG, "onSuccess:-- " + result);
    }
}
