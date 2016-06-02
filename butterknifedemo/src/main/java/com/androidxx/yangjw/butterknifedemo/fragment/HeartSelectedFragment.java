package com.androidxx.yangjw.butterknifedemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidxx.yangjw.butterknifedemo.DetailsActivity;
import com.androidxx.yangjw.butterknifedemo.R;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HeartSelectedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HeartSelectedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeartSelectedFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<String> groupData = new ArrayList<>();
    private Map<String,List<String>> childDataMap = new HashMap<>();
    private MyExpandAdapter mExpandAdapter;
    private Context mContext;
    private List<Integer> urls = new ArrayList<>();
    private HeaderViewHolder headerViewHolder ;
    private List<Integer> recyclerDatas = new ArrayList<>();
    private MyRecyclerAdapter myRecyclerAdapter;

    public HeartSelectedFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.selected_expand_listview)
    PullToRefreshExpandableListView mExpandListView;

    private ExpandableListView listView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeartSelectedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HeartSelectedFragment newInstance(String param1, String param2) {
        HeartSelectedFragment fragment = new HeartSelectedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_heart_selected, container, false);
        ButterKnife.bind(this,view);
        setupHeaderView ();
        setupExpandListView();
        initData();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        headerViewHolder.convenientBanner.startTurning(3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        headerViewHolder.convenientBanner.stopTurning();
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<RecViewHolder> {

        @Override
        public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(getActivity());

            return new RecViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(RecViewHolder holder, int position) {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        }

        @Override
        public int getItemCount() {
            return recyclerDatas.size();
        }
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            recyclerDatas.add(R.mipmap.ic_launcher);
        }
        myRecyclerAdapter.notifyDataSetChanged();
    }

    class RecViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public RecViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }

    private void setupHeaderView () {
        urls.add(R.drawable.a1);
        urls.add(R.drawable.a2);

        View headerview = LayoutInflater.from(mContext).inflate(R.layout.listview_header_view, null);
       headerViewHolder = new HeaderViewHolder(headerview);

        headerViewHolder.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        myRecyclerAdapter = new MyRecyclerAdapter();
        headerViewHolder.mRecyclerView.setAdapter(myRecyclerAdapter);
        headerViewHolder.convenientBanner.setPages(new CBViewHolderCreator<MyBannerHolder>() {
            @Override
            public MyBannerHolder createHolder() {
                return new MyBannerHolder();
            }
        },urls)
        .setPageIndicator(new int[] {R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_focused})
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        mExpandListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ExpandableListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                mExpandListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {

            }
        });
        listView = mExpandListView.getRefreshableView();
        listView.addHeaderView(headerview);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                startActivity(intent);

                return true;
            }
        });

    }

    private class MyBannerHolder implements Holder<Integer> {
        ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer idRes) {
            imageView.setImageResource(idRes);
        }
    }

    class HeaderViewHolder {
        @BindView(R.id.home_header_bannner)
        public ConvenientBanner convenientBanner;
        @BindView(R.id.home_header_recyclerview)
        public RecyclerView mRecyclerView;

        public HeaderViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    private void setupExpandListView() {
        //1、准备数据源
        //2、创建适配器
        mExpandAdapter = new MyExpandAdapter();
        //3、关联适配器
        listView.setAdapter(mExpandAdapter);
        initGroupData();

        //设置所有的GroupItem全部展开
        for (int i = 0; i < groupData.size(); i++) {
            listView.expandGroup(i);
        }

        //设置ExpandableListView的GroupItem点击事件失效
//        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                return true;
//            }
//        });


    }

//    @OnItemClick(value = R.id.selected_expand_listview)
//    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//        Toast.makeText(mContext, "ssss", Toast.LENGTH_SHORT).show();
//        return true;
//    }

    private void initGroupData() {
        for (int i = 0; i < 6; i++) {
            groupData.add("分组" + i);
            ArrayList<String> childArrayData = new ArrayList<String>();
            for (int j = 0; j < 10-i; j++) {
                childArrayData.add("分组" + i + "子项" + j);
            }
            childDataMap.put(""+i,childArrayData);
        }

        mExpandAdapter.notifyDataSetChanged();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
//        else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    class MyExpandAdapter extends BaseExpandableListAdapter {

        /**
         * 分组个数
         * @return
         */
        @Override
        public int getGroupCount() {
            return groupData == null ? 0 : groupData.size();
        }

        /**
         * 每一个组中的Child个数
         * @param groupPosition
         * @return
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            List<String> child = childDataMap.get("" + groupPosition);
            return child == null ? 0 :child.size() ;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
           View view = convertView;
            GroupViewHolder groupViewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.home_group_item_view,null);
                groupViewHolder = new GroupViewHolder(view);
            } else {
                groupViewHolder = (GroupViewHolder) view.getTag();
            }
            groupViewHolder.dateText.setText("2015-06-06 周三");
            return view;
        }

        class GroupViewHolder {
            @BindView(R.id.home_group_item_date_txt)
            public TextView dateText;
            @BindView(R.id.home_group_item_state_txt)
            public TextView stateText;

            public GroupViewHolder(View view) {
                view.setTag(this);
                ButterKnife.bind(this,view);
            }
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View childView = convertView;
            ChildViewHolder viewHolder = null;
            if (childView == null) {
                childView = LayoutInflater.from(mContext).inflate(R.layout.home_child_item_view,null);
                viewHolder = new ChildViewHolder(childView);
            } else {
                viewHolder = (ChildViewHolder) childView.getTag();
            }
            viewHolder.imageView.setImageResource(R.drawable.a12);
            return childView;
        }

        class ChildViewHolder {
            @BindView(R.id.child_item_img)
            public ImageView imageView;

            public ChildViewHolder(View view) {
                view.setTag(this);
                ButterKnife.bind(this,view);
            }

            @OnClick(R.id.child_item_img)
            public void click(View view) {
                Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                startActivity(intent);
            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }


}
