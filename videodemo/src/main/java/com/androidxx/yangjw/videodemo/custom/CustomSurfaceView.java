package com.androidxx.yangjw.videodemo.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by yangjw on 2016/6/3.
 *
 * 自定义SurfaceView的步骤：
 * 1、创建一个SurfaceView的子类
 * 2、获得SurfaceView中的Hodler对象。
 * 3、给Hodler对象设置回调监听SurfaceHolder.CallBack:当Holder对象初始化完毕之后，会执行此监听方法。
 * 4、在回调接口中执行surfaceCreated方法，将画图的操作在此方法中交给holder来完成
 *
 */
public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private Paint paint;

    public CustomSurfaceView(Context context) {
        this(context,null);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //找到秘书
//        SurfaceHolder surfaceHolder = getHolder();
        getHolder().addCallback(this);


    }

    private void draw(final SurfaceHolder holder) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        new Thread(new Runnable() {
                @Override
                public void run() {
                    //锁定画布，并且返回一个画布对象
                    Canvas canvas = holder.lockCanvas();
                    canvas.drawCircle(200,200,150,paint);
                    //释放画笔资源，并且将画布提交到SurfaceView中显示
                    holder.unlockCanvasAndPost(canvas);
                }
            }).start();
    }


    /**
     * SurfaceView初始化完成并且获得了一个Holder对象
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        draw(holder);
    }

    /**
     * SurfaceView的状态发生改变的时候执行此方法
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * SurfaceView销毁的时候执行此方法
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
