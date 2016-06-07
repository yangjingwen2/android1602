package com.androidxx.yangjw.jpushdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by yangjw on 2016/6/7.
 */
public class MyJpushReceiver extends BroadcastReceiver {

    private static final String TAG = "androidxx";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String extrasString = extras.getString(JPushInterface.EXTRA_MESSAGE);
        Log.d(TAG, "onReceive: " + extrasString);


    }
}
