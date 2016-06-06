package com.androidxx.yangjw.imdemo.chat;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidxx.yangjw.imdemo.R;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private EditText mMsgEdt;
    private Button mSendBtn;
    private RecyclerView mChatList;

    //创建数据源
    private ArrayList<EMMessage> messages = new ArrayList<>();
    private MyChatAdapter myChatAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myChatAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //注册消息接收的监听器
        EMClient.getInstance().chatManager().addMessageListener(emMessageListener);
        mMsgEdt = (EditText) findViewById(R.id.chat_msg_edt);
        mSendBtn = (Button) findViewById(R.id.chat_send_btn);
        mChatList = (RecyclerView) findViewById(R.id.chat_recycler_view);
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
        setupRecyclerView ();
    }

    private void setupRecyclerView () {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mChatList.setLayoutManager(linearLayoutManager);
        myChatAdapter = new MyChatAdapter();
        mChatList.setAdapter(myChatAdapter);

    }

    class MyChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

        @Override
        public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ChatActivity.this).inflate(R.layout.chat_item_layout, null);

            return new ChatViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ChatViewHolder holder, int position) {
            EMMessage emMessage = messages.get(position);
            String content = emMessage.getBody().toString();//获得聊天的信息
            holder.chatmsg.setText(content);
            //获得消息发送人
            String from = emMessage.getFrom();
            holder.username.setText(from);
        }

        @Override
        public int getItemCount() {
            return messages == null ? 0 : messages.size();
        }
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        public TextView username;
        public TextView chatmsg;
        public ChatViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.chat_item_usernmae);
            chatmsg = (TextView) itemView.findViewById(R.id.chat_item_msg);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //当不再需要接收消息时，移除监听事件
        EMClient.getInstance().chatManager().removeMessageListener(emMessageListener);
    }

    public void click() {
        String s = mMsgEdt.getText().toString();
        new SendMsgTask().execute(s);
    }


    class SendMsgTask extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... params) {

            //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
            EMMessage message = EMMessage.createTxtSendMessage(params[0], "yangjw");
            messages.add(message);
//如果是群聊，设置chattype，默认是单聊
//            if (chatType == CHATTYPE_GROUP)
//                message.setChatType(ChatType.GroupChat);

//发送消息
            EMClient.getInstance().chatManager().sendMessage(message);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            myChatAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 用来接收消息的监听事件
     */
    EMMessageListener emMessageListener = new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
//            String s = list.get(0).getBody().toString();
//            Log.d("android", "onMessageReceived: " + s);
            messages.addAll(list);
            //通知适配器刷新试图
//            myChatAdapter.notifyDataSetChanged();
            mHandler.sendEmptyMessageDelayed(1,1000);
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageReadAckReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageDeliveryAckReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };
}
