package com.androidxx.yangjw.butterknifedemo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.androidxx.yangjw.butterknifedemo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    public HeartSelectedFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.selected_expand_listview)
    ExpandableListView mExpandListView;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_heart_selected, container, false);
        ButterKnife.bind(this,view);
        setupExpandListView();
        return view;
    }

    private void setupExpandListView() {
        //1、准备数据源
        //2、创建适配器
        mExpandAdapter = new MyExpandAdapter();
        //3、关联适配器
        mExpandListView.setAdapter(mExpandAdapter);
        initGroupData();

        //设置所有的GroupItem全部展开
        for (int i = 0; i < groupData.size(); i++) {
            mExpandListView.expandGroup(i);
        }

        //设置ExpandableListView的GroupItem点击事件失效
        mExpandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

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
            TextView view = (TextView) convertView;
            if (view == null) {
                view = new TextView(getActivity());
            }
            view.setText("GROUP_ITEM" + groupPosition);
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            TextView childview = (TextView) convertView;
            if (childview == null) {
                childview = new TextView(getActivity());
            }
            childview.setText(childDataMap.get(groupPosition+"").get(childPosition));
            return childview;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }


}
