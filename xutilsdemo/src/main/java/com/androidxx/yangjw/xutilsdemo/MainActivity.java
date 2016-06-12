package com.androidxx.yangjw.xutilsdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.xutilsdemo.adapter.ConvertAdapter;
import com.androidxx.yangjw.xutilsdemo.adapter.IBindAdapter;
import com.androidxx.yangjw.xutilsdemo.bean.ProductInfo;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IBindAdapter{

    private static final String TAG = "androidxx";
    public ListView mListView;
    private ProgressDialog mProgressDialog;
    private List<ProductInfo.DataBean.ItemsBean> listDatas = new ArrayList<>();
    public ConvertAdapter mConvertAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setupListView();
        loadData();
    }

    private void setupListView() {
        //创建适配器
        mConvertAdapter = new ConvertAdapter(this);
        mConvertAdapter.setData(listDatas);
        mListView.setAdapter(mConvertAdapter);

    }

    /**
     * 初始化控件
     * create by yangjw at 2016.6.12
     * 添加了一些控件的初始化
     * modified by liurui at 2016.6.18
     *
     */
    private void initView() {
        mListView = (ListView) findViewById(R.id.xutils_listview);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在努力加载~");
    }

    /**
     * 加载商品列表的数据
     */
    private void loadData() {
        mProgressDialog.show();
        RequestParams requestParams = new RequestParams(UrlConfig.PRODUCT_LIST_URL);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            /**
             * 网络请求成功后回调此方法
             * @param result
             */
            @Override
            public void onSuccess(String result) {
                //执行在主线程中
                Log.d(TAG, "onSuccess: " + result);
                Gson gson = new Gson();
                ProductInfo productInfo = gson.fromJson(result, ProductInfo.class);
                listDatas.addAll(productInfo.getData().getItems());
                mConvertAdapter.notifyDataSetChanged();
            }

            /**
             * 网络请求出错
             * @param ex
             * @param isOnCallback
             */
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            /**
             * 取消网络请求
             * @param cex
             */
            @Override
            public void onCancelled(CancelledException cex) {

            }

            /**
             * 请求结束
             */
            @Override
            public void onFinished() {
                mProgressDialog.dismiss();
            }
        });
    }

    /**
     * 创建视图
     * @return
     */
    @Override
    public View createView() {
        return new TextView(this);
    }

    /**
     * 绑定视图
     * @param view
     */
    @Override
    public void bindView(View view,int position) {
        TextView textView = (TextView) view;
        String name = listDatas.get(position).getData().getName();
        textView.setText(name);
    }
}
