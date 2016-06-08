package com.androidxx.yangjw.shoppingcartdemo.utils;

import com.google.gson.Gson;

/**
 * Created by yangjw on 2016/6/8.
 */
public class JsonTool {

    public static <T>T parseJson2Object(String json , Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
