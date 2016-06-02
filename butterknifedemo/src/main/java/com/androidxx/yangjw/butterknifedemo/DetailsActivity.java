package com.androidxx.yangjw.butterknifedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
    }

    @OnItemClick(R.id.listview)
    public void onItemClick(int position) {
        Toast.makeText(DetailsActivity.this, "list", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.details_btn)
    public void send(View view) {
        Toast.makeText(DetailsActivity.this, "ssss", Toast.LENGTH_SHORT).show();
    }
}
