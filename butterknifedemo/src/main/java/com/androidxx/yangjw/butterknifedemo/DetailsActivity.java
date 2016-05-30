package com.androidxx.yangjw.butterknifedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.details_btn)
    public void send(View view) {
        Toast.makeText(DetailsActivity.this, "ssss", Toast.LENGTH_SHORT).show();
    }
}
