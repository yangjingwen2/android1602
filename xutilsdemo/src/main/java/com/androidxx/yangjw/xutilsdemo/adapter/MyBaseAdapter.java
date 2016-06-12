package com.androidxx.yangjw.xutilsdemo.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by yangjw on 2016/6/12.
 */
public abstract class MyBaseAdapter extends BaseAdapter{

    private List datas ;
    public void setData(List dataParams) {
        this.datas = dataParams;
    }

    public List getData() {
        return datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
