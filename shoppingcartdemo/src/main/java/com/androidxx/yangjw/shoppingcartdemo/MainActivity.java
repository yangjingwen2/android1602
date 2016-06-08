package com.androidxx.yangjw.shoppingcartdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.cart.orm.DaoMaster;
import com.androidxx.cart.orm.DaoSession;
import com.androidxx.cart.orm.ShoppingCard;
import com.androidxx.cart.orm.ShoppingCardDao;
import com.androidxx.yangjw.httplibrary.IOkTaskCallback;
import com.androidxx.yangjw.httplibrary.OkHttpTask;
import com.androidxx.yangjw.shoppingcartdemo.bean.ProductBean;
import com.androidxx.yangjw.shoppingcartdemo.cart.ShoppingCartActivity;
import com.androidxx.yangjw.shoppingcartdemo.config.UrlConfig;
import com.androidxx.yangjw.shoppingcartdemo.dao.DaoUtils;
import com.androidxx.yangjw.shoppingcartdemo.utils.JsonTool;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOkTaskCallback{

    private ListView mListView;
    private ProgressDialog mProgressDialog;
    private List<ProductBean.DataBean.ItemsBean> productDatas = new ArrayList<>();
    public ProductAdapter mProductAdapter;
    public Button mCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.product_list_lv);
        initView();
        setupListView();
        loadData();
    }

    private void setupListView() {
        mProductAdapter = new ProductAdapter();
        mListView.setAdapter(mProductAdapter);
    }

    private void initView() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("正在加载~");
        mProgressDialog.setMessage("客官进来坐啊~");

        mCartBtn = (Button) findViewById(R.id.product_list_cart_btn);
        mCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        mProgressDialog.show();
        OkHttpTask.newInstance(this).enqueue(this).start(UrlConfig.PRODUCT_LIST_URL);
    }


    @Override
    public void onSuccess(String result) {
        mProgressDialog.dismiss();
        ProductBean productBean = JsonTool.parseJson2Object(result, ProductBean.class);
        if (productBean != null && productBean.getData() != null) {
            productDatas.addAll(productBean.getData().getItems());
            mProductAdapter.notifyDataSetChanged();
        }
    }

    class ProductAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return productDatas == null ? 0 : productDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder viewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.product_list_item,null);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            ProductBean.DataBean.ItemsBean itemsBean = productDatas.get(position);
            viewHolder.mProductNamtTv.setText(itemsBean.getData().getName());
            viewHolder.mProductPriceTv.setText(itemsBean.getData().getPrice());
            viewHolder.mJoinCartBtn.setTag(position);
            return view;
        }

        class ViewHolder implements View.OnClickListener{
            public TextView mProductNamtTv;
            public TextView mProductPriceTv;
            public Button mJoinCartBtn;

            public ViewHolder(View view) {
                view.setTag(this);
                mProductNamtTv = (TextView) view.findViewById(R.id.product_list_item_name_tv);
                mProductPriceTv = (TextView) view.findViewById(R.id.product_list_item_price_tv);
                mJoinCartBtn = (Button) view.findViewById(R.id.product_list_item_join_cart_btn);
                mJoinCartBtn.setOnClickListener(this);
            }


            @Override
            public void onClick(View v) {
               int position = (int) v.getTag();
                ProductBean.DataBean.ItemsBean itemsBean = productDatas.get(position);
                ShoppingCard shoppingCard = new ShoppingCard();
                shoppingCard.setProductId(Long.valueOf(itemsBean.getData().getId()));
                shoppingCard.setProductName(itemsBean.getData().getName());
                shoppingCard.setProductPrice(Float.valueOf(itemsBean.getData().getPrice()));
                shoppingCard.setProductNum(1);
                insertCart(shoppingCard);
            }
        }
    }

    private void insertCart(ShoppingCard shoppingCard) {
        ShoppingCardDao shoppingCardDao = DaoUtils.getSession(this).getShoppingCardDao();
        shoppingCardDao.insert(shoppingCard);


    }
}
