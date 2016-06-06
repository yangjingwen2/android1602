package com.androidxx.yangjw.imdemo.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidxx.yangjw.imdemo.R;
import com.androidxx.yangjw.imdemo.group.GroupActivity;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void click(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.index_single_chat:
                intent.setClass(this,FriendsActivity.class);
                break;
            case R.id.index_group_chat:
                intent.setClass(this,GroupActivity.class);
                break;
        }
        startActivity(intent);
    }
}
