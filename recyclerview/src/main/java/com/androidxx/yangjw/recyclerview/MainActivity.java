package com.androidxx.yangjw.recyclerview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.recycler_btn)
    Button mAddBtn;

    @OnClick(R.id.recycler_btn)
    public void click(View view) {
//        datas.remove(0);
//        datas.add(0,R.drawable.a4);
//        datas.remove(1);
        datas.add(1,R.drawable.a3);
        myRecyclerAdapter.notifyItemInserted(1);
//        myRecyclerAdapter.notifyItemInserted(1);
//        myRecyclerAdapter.notifyDataSetChanged();
//        myRecyclerAdapter.notifyDataSetChanged();
    }

    private List<Integer> datas = new ArrayList<>();
    private MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //添加分割线
//        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
//                .color(Color.RED).build());
        //添加动画
        mRecyclerView.setItemAnimator(new SlideInLeftAnimator((new BounceInterpolator())));
        mRecyclerView.getItemAnimator().setAddDuration(2000);


        /**
         * 1、指定一个布局管理器
         * 2、绑定一个适配器
         */
        //创建一个线性布局管理器
        //参数一：上下文对象
        //参数二：线性布局的方向
        //参数三：表示升序排列或者降序排列，false表示升序，true表示降序
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //创建适配器
        /**
         * 1、创建一个ViewHolder继承RecyclerView.ViewHolder类
         * 2、创建一个MyAdapter集成RecyclerView.Adapter类
         */
        initData() ;
        myRecyclerAdapter = new MyRecyclerAdapter();
        //关联适配器
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new SlideInLeftAnimationAdapter(myRecyclerAdapter));
    }

    /**
     *
     */
    private void setupHeaderView() {

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            datas.add(R.drawable.a1);
//            datas.add(R.drawable.a2);
//            datas.add(R.drawable.a3);
//            datas.add(R.drawable.a4);
        }

    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
        public static final int HEADER_VIEW = 1;
        public static final int ITEM_VIEW = 2;
        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return HEADER_VIEW;
            }
            return ITEM_VIEW;
        }

        /**
         * 创建布局文件和创建ViewHodler
         * @param parent
         * @param viewType 由getItemViewType返回的
         * @return
         */
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            if (viewType == HEADER_VIEW) {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.header_view,parent,false);
                return new MyViewHolder(view);
            }

            view = LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_item, parent, false);
            return new MyViewHolder(view);
        }

        /**
         * 复用布局文件和ViewHolder
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if (position>0) {
                holder.itemImage.setImageResource(datas.get(position));
            }
        }

        /**
         * 返回数据源的大小
         * @return
         */
        @Override
        public int getItemCount() {
            return datas == null ? 0 : datas.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.item_image)
        public ImageView itemImage;

        public MyViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this,itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.item_image);
        }

//        @OnClick(R.id.item_image)
//        public void click(View view) {
//
//        }
    }
}
