package com.androidxx.yangjw.videodemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class HolderSurfaceViewActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private SurfaceView mSurfaceView;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder_surface_view);
        mSurfaceView = (SurfaceView) findViewById(R.id.holder_surface_view);

        //获得Holder对象
        SurfaceHolder holder = mSurfaceView.getHolder();
        //当Holder初始化完成之后，回调监听方法
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        paint = new Paint();
        paint.setColor(Color.RED);
        Canvas canvas = holder.lockCanvas();
        canvas.drawCircle(200,200,150,paint);
        holder.unlockCanvasAndPost(canvas);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
