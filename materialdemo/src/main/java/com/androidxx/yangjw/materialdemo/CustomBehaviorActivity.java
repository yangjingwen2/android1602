package com.androidxx.yangjw.materialdemo;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.androidxx.yangjw.materialdemo.custom.CustomImageView;

public class CustomBehaviorActivity extends AppCompatActivity {

    private CustomImageView mImageView;
    private ImageView ImageTop;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_behavior);
        mImageView = (CustomImageView) findViewById(R.id.dependency_image);
        ImageTop = (ImageView) findViewById(R.id.dependency_top_image);
        mButton = (Button) findViewById(R.id.button1);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(v,10);
            }
        });
        ImageTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(v,20);
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(v,-20);
            }
        });
    }
}
