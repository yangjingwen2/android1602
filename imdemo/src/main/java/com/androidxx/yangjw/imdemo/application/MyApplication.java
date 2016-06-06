package com.androidxx.yangjw.imdemo.application;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

/**
 * Created by yangjw on 2016/6/6.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //还信初始化
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
        options.setAutoLogin(false);
        //如果有第3方的启动项（比如百度地图），则需要根据官网添加判断语句
        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
