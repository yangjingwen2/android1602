package com.androidxx.yangjw.mvcdemo.model.impl;

import android.content.Context;

import com.androidxx.yangjw.mvcdemo.controller.IProductInfoView;
import com.androidxx.yangjw.mvcdemo.model.IProductModel;

/**
 * Created by yangjw on 2016/6/1.
 */
public class Product2Model implements IProductModel{
    @Override
    public void loadAllProductData(Context context, IProductInfoView productInfoView) {
        //换另一个网络请求框架
    }

    @Override
    public void loadProductDetailsInfo(Context context, int id) {

    }
}
