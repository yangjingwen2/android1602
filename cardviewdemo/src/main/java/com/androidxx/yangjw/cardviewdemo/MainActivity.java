package com.androidxx.yangjw.cardviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.card_imageview);
        Picasso.with(this)
                .load("http://i3.72g.com/upload/201510/201510261436311062.png")
                .placeholder(R.drawable.a4)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
