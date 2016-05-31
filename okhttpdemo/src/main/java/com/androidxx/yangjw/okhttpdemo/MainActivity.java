package com.androidxx.yangjw.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "androidxx";
    private Button mGetBtn;
    private Button mAysncGetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGetBtn = (Button) findViewById(R.id.okhttp_get_btn);
        mAysncGetBtn = (Button) findViewById(R.id.okhttp_get_async_btn);
        mGetBtn.setOnClickListener(this);
        mAysncGetBtn.setOnClickListener(this);
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
        }
//

    }
}
