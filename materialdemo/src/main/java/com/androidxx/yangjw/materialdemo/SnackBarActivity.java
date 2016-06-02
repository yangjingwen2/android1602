package com.androidxx.yangjw.materialdemo;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

public class SnackBarActivity extends AppCompatActivity {

    private CoordinatorLayout rootview;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
        rootview = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        frameLayout = (FrameLayout) findViewById(android.R.id.content);
    }

    public void click(View view){
        /**
         * 参数一：就是给SnackBar指定一个父容器
         */
        Snackbar.make(rootview,"我是一个SnackBar",Snackbar.LENGTH_INDEFINITE)
                .setDuration(Snackbar.LENGTH_INDEFINITE)
                //设置按钮和点击事件
                .setAction("知道了", new View.OnClickListener() {
                    public static final String TAG = "androidxx";

                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: this is a snack bar" );
                    }
                }).show();
    }
}
