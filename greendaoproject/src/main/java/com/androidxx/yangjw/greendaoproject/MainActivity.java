package com.androidxx.yangjw.greendaoproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidxx.yangjw.greendaoproject.orm.DaoMaster;
import com.androidxx.yangjw.greendaoproject.orm.DaoSession;
import com.androidxx.yangjw.greendaoproject.orm.User;
import com.androidxx.yangjw.greendaoproject.orm.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void click(View view) {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "android1602", null);
        SQLiteDatabase readableDatabase = openHelper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(readableDatabase);
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();


        User user = new User();
        user.setUserName("zhangsan");
        user.setUserAge(23);
        user.setUserSex("男");
        userDao.insert(user);

        //查询

        List<User> userList = userDao.loadAll();
        for (int i = 0; i < userList.size(); i++) {
            Log.d(TAG, "click: " + userList.get(i).getUserName());
        }

    }
}
