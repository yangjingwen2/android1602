package com.androidxx.yangjw.dependencyinjectionidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.androidxx.yangjw.dependencyinjectionidemo.ioc.BindView;
import com.androidxx.yangjw.dependencyinjectionidemo.ioc.X;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    public String name;
    @BindView(R.id.textview)
    public TextView textView;
    @BindView(R.id.button)
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        X.bind(this);

        Log.d(TAG, "onCreate: " + name);
        textView.setText("这是文本");
        button.setText("这是依赖注入创建的按钮");
    }
}
