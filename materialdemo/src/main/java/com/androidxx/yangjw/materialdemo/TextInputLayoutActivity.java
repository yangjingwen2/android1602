package com.androidxx.yangjw.materialdemo;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class TextInputLayoutActivity extends AppCompatActivity {

    private TextInputLayout mTextInputLayout;
    private EditText mUsernameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        mTextInputLayout = (TextInputLayout) findViewById(R.id.username_input_layout);
        mUsernameEdit = (EditText) findViewById(R.id.username_edt);
        mUsernameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mUsernameEdit.getText().toString().length()<6) {
                    mTextInputLayout.setErrorEnabled(true);
                    mTextInputLayout.setError("输入的字符个数不足6个");
                } else {
                    mTextInputLayout.setErrorEnabled(false);
                }
            }
        });
    }
}
