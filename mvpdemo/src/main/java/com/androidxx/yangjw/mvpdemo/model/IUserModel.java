package com.androidxx.yangjw.mvpdemo.model;

/**
 * Created by yangjw on 2016/6/1.
 */
public interface IUserModel {


    public String login(String username,String password);

    public String queryUserInfo(int id);
}
