package com.androidxx.yangjw.eventdispatchdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);

        //设置触摸事件
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://手指按下
                        Log.d(TAG, "onTouch: down");
                        break;
                    case MotionEvent.ACTION_UP://手指抬起
                        Log.d(TAG, "onTouch: up");
                        break;
                }
                return false;
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick");
                Intent intent  = new Intent(MainActivity.this,EventActivity.class);
                startActivity(intent);
            }
        });
    }
}
