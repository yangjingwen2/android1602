package com.androidxx.yangjw.dependencyinjectionidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.androidxx.yangjw.dependencyinjectionidemo.ioc.BindView;
import com.androidxx.yangjw.dependencyinjectionidemo.ioc.X;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    public String name;
    @BindView(R.id.second_text)
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        X.bind(this);
        textView.setText("sss");
    }
}
