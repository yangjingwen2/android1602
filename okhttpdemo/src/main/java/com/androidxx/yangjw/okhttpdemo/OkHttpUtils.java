package com.androidxx.yangjw.okhttpdemo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yangjw on 2016/5/31.
 */
public class OkHttpUtils {

    private static final String TAG = "androidxx";
    /**
     * OkHttp官方推荐一个APP中最好只有一个OkHttpClient对象
     * 例如：
     * 为了保证一个APP只有一个缓存
     */
    private static OkHttpClient okHttpClient ;
    private static ExecutorService executorService;
    private static Handler mHandler;
    private static IOkCallback mOkCallback;

    static {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(4);
        }
        mHandler = new MyOkHandler();
    }

    /**
     * Get请求
     * OkHttp中的同步Get请求
     */
    public static String okGet(final String address) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder().url(address).build();
                    Response response = okHttpClient.newCall(request).execute();//不能再UI线程中执行
                    String result = response.body().string();//此结果数据只能调用一次
                    Log.d(TAG, "run: " + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return null;
    }

    public static void okAsyncGet(String address,IOkCallback callback) {


        mOkCallback = callback;

        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败的回调接口
            }

            /**
             * 此方法执行在子线程中
             * @param call
             * @param response
             * @throws IOException
             */
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功的回调接口
                String result = response.body().string();
                mHandler.obtainMessage(1,result).sendToTarget();
            }
        });
    }

    static class MyOkHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: " + msg.obj.toString());
            mOkCallback.onSuccess(msg.obj.toString());
        }
    }
}
