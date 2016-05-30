package com.androidxx.yangjw.butterknifedemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.androidxx.yangjw.butterknifedemo.fragment.HeartSelectedFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_toolbar)
    Toolbar mToolBar;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolBar();
        setupFragment();
    }

    private void setupFragment() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        HeartSelectedFragment heartSelectedFragment = HeartSelectedFragment.newInstance("", "");
        fragmentTransaction.add(R.id.home_fragment_content_fl,heartSelectedFragment);
        fragmentTransaction.commit();
    }

    private void setupToolBar() {
        setSupportActionBar(mToolBar);
        setTitle("ssss");
    }
}
