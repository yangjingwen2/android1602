package com.androidxx.yangjw.materialdemo;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ShareElement1Activity extends AppCompatActivity {

    private ImageView mShareImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element1);
        mShareImage = (ImageView) findViewById(R.id.share_image);
        mShareImage.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareElement1Activity.this,ShareElement2Activity.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(ShareElement1Activity.this, v, "android");
                startActivity(intent,activityOptions.toBundle());
            }
        });
    }
}
