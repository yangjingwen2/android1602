package com.androidxx.yangjw.videodemo;

import android.graphics.Camera;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

public class SurfaceViewVideoActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private SurfaceView mSurfacrView;
    private MediaPlayer mediaPlayer;

    public static final String url = "http://i.snssdk.com/neihan/video/playback/?video_id=840aebabb21d4ed7a27dfd5f993f86e3&quality=360p&line=1&is_gif=0";
    /**
     *
     * @param savedInstanceState
     *
     * 1、MediaPlayer进行视屏的加载和解析
     * 2、SurfaceView显示视频图像
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view_video);

        //1、初始化SurfaceView对象
        mSurfacrView = (SurfaceView) findViewById(R.id.video_surfave_view);
        SurfaceHolder holder = mSurfacrView.getHolder();
        holder.addCallback(this);
        //2、解析视频数据并且显示到屏幕上
        mediaPlayer = new MediaPlayer();
        //配置视屏路径
        //参数一：上下文对象
        //参数二：Uri对象：就是视频源

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            //通过Holder类来创建视图
        Uri uri = Uri.parse(url);
        try {
            mediaPlayer.setDataSource(this,uri);
            //异步加载数据源
            mediaPlayer.prepareAsync();
            //将SurfaceView和MediaPlayer关联
            mediaPlayer.setDisplay(holder);
            //设置数据加载成功的回调接口
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                //此方法用于在视屏数据加载成功之后执行
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //释放资源
            mediaPlayer.release();
        //停止视屏播放
            mediaPlayer.stop();
    }
}
