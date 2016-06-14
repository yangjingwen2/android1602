package com.androidxx.yangjw.mvcdemo.model.impl;

import android.content.Context;

import com.androidxx.yangjw.httplibrary.IOkTaskCallback;
import com.androidxx.yangjw.httplibrary.OkHttpTask;
import com.androidxx.yangjw.mvcdemo.controller.IProductInfoView;
import com.androidxx.yangjw.mvcdemo.model.IProductModel;
import com.androidxx.yangjw.mvcdemo.model.bean.ProductBean;
import com.google.gson.Gson;

/**
 * Created by yangjw on 2016/6/1.
 */
public class ProductModel implements IProductModel {

    @Override
    public void loadAllProductData(Context context,final IProductInfoView productInfoView) {
        //请求网络
        OkHttpTask.newInstance(context).enqueue(new IOkTaskCallback() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ProductBean productBean = gson.fromJson(result, ProductBean.class);
                productInfoView.setData(productBean);
            }
        }).start("http://api.liwushuo.com/v2/channels/101/items?ad=2&gender=1&generation=2&limit=20&offset=0");
    }

    @Override
    public void loadProductDetailsInfo(Context context, int id) {
        //请求网络代码
    }


}
