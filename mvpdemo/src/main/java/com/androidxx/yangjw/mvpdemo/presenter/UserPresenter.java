package com.androidxx.yangjw.mvpdemo.presenter;

import com.androidxx.yangjw.mvpdemo.model.IUserModel;
import com.androidxx.yangjw.mvpdemo.model.impl.UserModel;
import com.androidxx.yangjw.mvpdemo.view.IUserView;

/**
 * Created by yangjw on 2016/6/1.
 */
public class UserPresenter implements IUserPresenter {

    private IUserView userView;
    private IUserModel userModel;

    public UserPresenter(IUserView userView) {
        this.userView = userView;
        userModel = new UserModel();
    }

    public void login() {
        String usernameFromEdt = userView.getUsernameFromEdt();
        String passwordFromEdt = userView.getPasswordFromEdt();
        if ("".equals(usernameFromEdt) || "".equals(passwordFromEdt)) {
            userView.loginResult("fail");
        } else {
            String login = userModel.login(usernameFromEdt, passwordFromEdt);
            userView.loginResult(login);
        }
    }

    @Override
    public void getUserInfo(int id) {
        String userInfo = userModel.queryUserInfo(id);
        if (userInfo.contains("23")) {
           userView.refreshUserInfoView(userInfo);
        }

    }
}
