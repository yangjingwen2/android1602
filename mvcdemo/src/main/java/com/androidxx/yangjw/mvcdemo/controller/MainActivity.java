package com.androidxx.yangjw.mvcdemo.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidxx.yangjw.mvcdemo.BaseActivity;
import com.androidxx.yangjw.mvcdemo.R;
import com.androidxx.yangjw.mvcdemo.model.IProductModel;
import com.androidxx.yangjw.mvcdemo.model.bean.ProductBean;
import com.androidxx.yangjw.mvcdemo.model.impl.Product2Model;
import com.androidxx.yangjw.mvcdemo.model.impl.ProductModel;

public class MainActivity extends BaseActivity {

     TextView view;
    private IProductModel productModel = new ProductModel();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupList();
        setupDetails();
    }

    void initView() {
        view = (TextView) findViewById(R.id.home_text);
    }

    void setupList() {
        productModel.loadAllProductData(this, new IProductInfoView() {
            @Override
            public void setData(ProductBean productBean) {
                view.setText(productBean.getMessage());
            }
        });
    }

    void setupDetails() {
        productModel.loadProductDetailsInfo(this,1);
        //更新视图
    }
}
