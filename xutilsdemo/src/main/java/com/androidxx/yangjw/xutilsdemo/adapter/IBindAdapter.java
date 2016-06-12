package com.androidxx.yangjw.xutilsdemo.adapter;

import android.view.View;

/**
 * Created by yangjw on 2016/6/12.
 */
public interface IBindAdapter {

    /**
     * 创建View
     * @return
     */
    public View createView();

    /**
     * 绑定View
     * @param view
     */
    public void bindView(View view,int position);
}
