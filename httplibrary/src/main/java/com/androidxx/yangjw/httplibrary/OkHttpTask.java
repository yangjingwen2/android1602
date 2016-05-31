package com.androidxx.yangjw.httplibrary;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yangjw on 2016/5/31.
 */
public abstract class OkHttpTask  {

    private static OkHttpClient okHttpClient;
    public static final String URL_REGEX = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";


    public static OkHttpTaskBuidler newInstance() {

        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder().build();
        }

        OkHttpTaskBuidler okHttpTask = new OkHttpTaskBuidler();
        return okHttpTask;
    }

    public static class OkHttpTaskBuidler extends AsyncTask<String,Integer,String>{

        private IOkTaskCallback callback;

        public void start(String url) {

            if (url == null) {
                throw new NullPointerException("url is null");
            }

            if (!url.matches(URL_REGEX)) {
                throw new IllegalArgumentException("URL is wrong");
            }
            this.execute(url);
        }

        public OkHttpTaskBuidler enqueue(IOkTaskCallback callback) {
            this.callback = callback;
            return OkHttpTaskBuidler.this;
        }



        /**
         * 执行请求网络
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            Request request = new Request.Builder().url(url).build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 接受网络返回的数据
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (callback != null) {
                callback.onSuccess(s);
            }

        }
    }

}
