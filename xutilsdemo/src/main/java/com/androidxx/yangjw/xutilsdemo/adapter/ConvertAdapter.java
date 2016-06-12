package com.androidxx.yangjw.xutilsdemo.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangjw on 2016/6/12.
 */
public class ConvertAdapter extends MyBaseAdapter{

    private IBindAdapter bindAdapter;

    public ConvertAdapter(IBindAdapter bindAdapter) {
        this.bindAdapter = bindAdapter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            //创建View
            view = bindAdapter.createView();
        }
        //初始化数据，使用View
        bindAdapter.bindView(view,position);

        return view;
    }
}
