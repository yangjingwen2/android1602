package com.androidxx.yangjw.materialdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.index_list);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(this,CoordinatorActivity.class);
                break;
            case 1:
                intent.setClass(this,CustomBehaviorActivity.class);
                break;
            case 2:
                intent.setClass(this,APPBarLayoutActivity.class);
                break;
            case 3:
                intent.setClass(this,CollapsingToolBarLayoutActivity.class);
                break;
        }

        startActivity(intent);
    }
}
