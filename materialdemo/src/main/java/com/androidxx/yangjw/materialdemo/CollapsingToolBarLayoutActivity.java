package com.androidxx.yangjw.materialdemo;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

public class CollapsingToolBarLayoutActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private CollapsingToolbarLayout mCollapsingToolLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_tool_bar_layout);
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);
        mCollapsingToolLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_tool_layout);
        mCollapsingToolLayout.setCollapsedTitleTextColor(Color.WHITE);
        mCollapsingToolLayout.setExpandedTitleColor(Color.RED);
        mCollapsingToolLayout.setExpandedTitleGravity(Gravity.CENTER_HORIZONTAL);
        mCollapsingToolLayout.setCollapsedTitleGravity(Gravity.CENTER_HORIZONTAL);
    }
}
