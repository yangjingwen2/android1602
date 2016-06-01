package com.androidxx.yangjw.mvpdemo.model.impl;

import com.androidxx.yangjw.mvpdemo.model.IUserModel;

/**
 * Created by yangjw on 2016/6/1.
 */
public class UserModel implements IUserModel {
    @Override
    public String login(String username, String password) {
        //
        if ("zhangsan".equals(username) && "123456".equals(password)) {
            return "success";
        }
        return "fail";
    }

    @Override
    public String queryUserInfo(int id) {
        return "zhangsan 23 nan";
    }
}
