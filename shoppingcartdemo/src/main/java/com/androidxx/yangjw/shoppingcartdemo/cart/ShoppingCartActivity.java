package com.androidxx.yangjw.shoppingcartdemo.cart;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.androidxx.yangjw.shoppingcartdemo.R;
import com.androidxx.yangjw.shoppingcartdemo.alipay.PayTool;
import com.androidxx.yangjw.shoppingcartdemo.bean.ProductBean;
import com.androidxx.yangjw.shoppingcartdemo.dao.DaoUtils;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    public ListView mCartListView;
    private List<ShoppingCard> shoppingCartsDatas = new ArrayList<>();
    public CartAdapter mCartAdapter;
    public Button mPayBtn;
    private float totalPrice;
    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        initView();
        loadAllCartData();
    }

    private void initView() {
        mProgressDialog = new ProgressDialog(this);
        mCartListView = (ListView) findViewById(R.id.cart_list_lv);
        mPayBtn = (Button) findViewById(R.id.cart_list_pay_btn);
        mCartAdapter = new CartAdapter();
        mCartListView.setAdapter(mCartAdapter);
        mPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPrice = 0;
                for (int i = 0; i < shoppingCartsDatas.size(); i++) {
                    ShoppingCard shoppingCard = shoppingCartsDatas.get(i);
                    totalPrice += shoppingCard.getProductNum() * shoppingCard.getProductPrice();

                }

                if (totalPrice > 0) {
                    //支付代码
                    mProgressDialog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            String result = PayTool.pay(ShoppingCartActivity.this,"礼物说","总共有" + shoppingCartsDatas.size() + "件商品",0.01f);
                            Log.d("androidxx", "run: " + result);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressDialog.dismiss();
                                }
                            });
                        }
                    }).start();
                }
            }
        });
    }

    private void loadAllCartData() {
        ShoppingCardDao shoppingCardDao = DaoUtils.getSession(this).getShoppingCardDao();
        List<ShoppingCard> shoppingCards = shoppingCardDao.loadAll();
        shoppingCartsDatas.addAll(shoppingCards);
        mCartAdapter.notifyDataSetChanged();

    }

    class CartAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return shoppingCartsDatas == null ? 0 : shoppingCartsDatas.size();
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
                view = LayoutInflater.from(ShoppingCartActivity.this).inflate(R.layout.cart_list_item,null);
                viewHolder = new ViewHolder(view);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            ShoppingCard shoppingCard = shoppingCartsDatas.get(position);
            viewHolder.mProductNamtTv.setText(shoppingCard.getProductName());
            viewHolder.mProductPriceTv.setText("$"+shoppingCard.getProductPrice());
            viewHolder.mNumTv.setText(shoppingCard.getProductNum()+"");
            viewHolder.mMinusBtn.setTag(position);
            viewHolder.mPlusBtn.setTag(position);
            return view;
        }

        class ViewHolder implements View.OnClickListener{
            public TextView mProductNamtTv;
            public TextView mProductPriceTv;
            public Button mMinusBtn;
            public TextView mNumTv;
            public Button mPlusBtn;

            public ViewHolder(View view) {
                view.setTag(this);
                mProductNamtTv = (TextView) view.findViewById(R.id.product_list_item_name_tv);
                mProductPriceTv = (TextView) view.findViewById(R.id.product_list_item_price_tv);
                mMinusBtn = (Button) view.findViewById(R.id.cart_item_minus);
                mNumTv = (TextView) view.findViewById(R.id.cart_item_num);
                mPlusBtn = (Button) view.findViewById(R.id.cart_item_plus);
                mMinusBtn.setOnClickListener(this);
                mPlusBtn.setOnClickListener(this);
            }


            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                int num = shoppingCartsDatas.get(position).getProductNum();
                switch (v.getId()) {
                    case R.id.cart_item_minus:
                        num = --num <= 0 ? 1 : num;
                        shoppingCartsDatas.get(position).setProductNum(num);
                        break;
                    case R.id.cart_item_plus:
                        shoppingCartsDatas.get(position).setProductNum(++num);
                        break;
                }

                mNumTv.setText(shoppingCartsDatas.get(position).getProductNum()+"");
                ShoppingCard shoppingCard = shoppingCartsDatas.get(position);
                ShoppingCardDao shoppingCardDao = DaoUtils.getSession(ShoppingCartActivity.this).getShoppingCardDao();
                shoppingCardDao.update(shoppingCard);
            }
        }
    }
}
