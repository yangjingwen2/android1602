package com.androidxx.yangjw.xutilsdemo.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by yangjw on 2016/6/12.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Xutils初始化：如果需要使用注解Xutils初始化是必须配置的
        //如果仅仅使用网络请求，此初始化可以不要（待测试）
        x.Ext.init(this);

    }
}
