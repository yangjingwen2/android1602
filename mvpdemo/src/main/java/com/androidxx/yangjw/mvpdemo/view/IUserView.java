package com.androidxx.yangjw.mvpdemo.view;

/**
 * Created by yangjw on 2016/6/1.
 */
public interface IUserView {

    public String getUsernameFromEdt();
    public String getPasswordFromEdt();

    public void loginResult(String result);

    public void refreshUserInfoView(String userinfo);
}
