package com.androidxx.yangjw.jshtmldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //参数一：指定Javascript的接口对象
        //参数二：window.参数二.方法名称（通过@JavascriptInterface指定）
        mWebView.addJavascriptInterface(new UserModel(this),"jsObj");
//        mWebView.loadUrl("http://10.6.155.104:8080/HtmlDemo/login.html");
        mWebView.loadUrl("http://www.u17.com/z/zt/appspecial/special_comic_list_v3.html?is_comment=1&special_id=40&android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia&android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia");
    }
}
