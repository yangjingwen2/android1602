package com.androidxx.yangjw.mvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidxx.yangjw.mvpdemo.presenter.IUserPresenter;
import com.androidxx.yangjw.mvpdemo.presenter.UserPresenter;
import com.androidxx.yangjw.mvpdemo.view.IUserView;

public class MainActivity extends AppCompatActivity implements IUserView{

    private EditText usernameEdt;
    private EditText passwordEdt;
    private Button mSubmitBtn;
    private IUserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        userPresenter = new UserPresenter(this);
    }

    private void initView() {
        usernameEdt = (EditText) findViewById(R.id.login_username_edt);
        passwordEdt = (EditText) findViewById(R.id.login_password_edt);
        mSubmitBtn = (Button) findViewById(R.id.login_submit_btn);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.login();
            }
        });
    }

    @Override
    public String getUsernameFromEdt() {
        return usernameEdt.getText().toString();
    }

    @Override
    public String getPasswordFromEdt() {
        return passwordEdt.getText().toString();
    }

    @Override
    public void loginResult(String result) {
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshUserInfoView(String userinfo) {
        passwordEdt.setText(userinfo);
    }
}
