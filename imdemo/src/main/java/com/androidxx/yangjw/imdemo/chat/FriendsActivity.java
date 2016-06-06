package com.androidxx.yangjw.imdemo.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.androidxx.yangjw.imdemo.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private ListView mListView;
    private List<String> userList = new ArrayList<>();
    private FriendsAdapter friendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        mListView = (ListView) findViewById(R.id.friends_list);
        friendsAdapter = new FriendsAdapter();
        mListView.setAdapter(friendsAdapter);
        loadAllUser();
    }

    private void loadAllUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    EMClient.getInstance().contactManager().addContact("android", "ssssss");
                    final List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    userList.addAll(usernames);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            friendsAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    class FriendsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return userList.size();
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
            if ( view == null) {

                view = new TextView(FriendsActivity.this);
            }
            view.setText(userList.get(position));
            return view;
        }
    }
}
