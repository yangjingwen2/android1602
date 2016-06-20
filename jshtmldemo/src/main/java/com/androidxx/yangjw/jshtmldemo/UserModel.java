package com.androidxx.yangjw.jshtmldemo;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by yangjw on 2016/6/20.
 */
public class UserModel {

    private Context context;

    public UserModel(Context context) {
        this.context = context;
    }

    //指定当前方法为Javascript的接口
    @JavascriptInterface
    public void login(String username, String password) {
        Toast.makeText(context, username + "/" + password, Toast.LENGTH_SHORT).show();
    }


    @JavascriptInterface
    public void startComicDetail(long id) {
        Toast.makeText(context, "" + id, Toast.LENGTH_SHORT).show();
    }
}
