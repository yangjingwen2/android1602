package com.androidxx.yangjw.mvcdemo.model;

import android.content.Context;

import com.androidxx.yangjw.mvcdemo.controller.IProductInfoView;

/**
 * Created by yangjw on 2016/6/1.
 */
public interface IProductModel {

    public void loadAllProductData(Context context,IProductInfoView productInfoView);

    public void loadProductDetailsInfo(Context context,int id);
}
