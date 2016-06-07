package com.androidxx.yangjw.jpushdemo;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by yangjw on 2016/6/7.
 */
public class MyApplciation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化推送
        JPushInterface.init(this);
    }
}
