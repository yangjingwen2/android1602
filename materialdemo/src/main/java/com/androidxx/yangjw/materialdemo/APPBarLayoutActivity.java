package com.androidxx.yangjw.materialdemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class APPBarLayoutActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private Toolbar mToolBar;
    private AppBarLayout mAppBarLayout;
    private ImageView mAnchorImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar_layout);
        mToolBar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(mToolBar);
        mAnchorImage = (ImageView) findViewById(R.id.anchor_image);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        setupListener();
    }

    private void setupListener() {
        //监听AppBarLayout垂直偏移量的监听事件
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "onOffsetChanged: " + verticalOffset);
//                mAnchorImage.setImageAlpha(verticalOffset);
                if (verticalOffset < -1000) {
                    mAnchorImage.setVisibility(View.GONE);
                } else {
                    mAnchorImage.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
