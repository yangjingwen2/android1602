package com.androidxx.yangjw.imdemo.group;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.imdemo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity {

    private ListView mGroupListView;
    private List<EMGroup> groupDatas = new ArrayList<>();
    private MyGroupAdapter myGroupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        mGroupListView = (ListView) findViewById(R.id.group_list);
        myGroupAdapter = new MyGroupAdapter();
        mGroupListView.setAdapter(myGroupAdapter);
        loadGroupListData();
        setupListView();
    }

    private void setupListView() {
        mGroupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (groupDatas != null && groupDatas.size() > position
                        && groupDatas.get(position) != null) {
                    Intent intent = new Intent(GroupActivity.this,GroupChatActivity.class);
                    intent.putExtra("group_id",groupDatas.get(position).getGroupId());
                    startActivity(intent);
                }

            }
        });
    }

    private void loadGroupListData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //从服务器获取自己加入的和创建的群组列表，此api获取的群组sdk会自动保存到内存和db。
                try {
                   final List<EMGroup> grouplist = EMClient.getInstance().groupManager().getJoinedGroupsFromServer();//需异步处理
                    Log.d("androidxx", "run: " + grouplist.size());


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            groupDatas.addAll(grouplist);
                            myGroupAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    class MyGroupAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return groupDatas == null ? 0 : groupDatas.size();
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
            TextView view = (TextView) convertView;
            if (view == null) {
                view = new TextView(GroupActivity.this);
            }

            EMGroup emGroup = groupDatas.get(position);
            String groupName = emGroup.getGroupName();
            view.setText(groupName);

            return view;
        }
    }
}
