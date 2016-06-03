package com.androidxx.yangjw.videodemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidxx.yangjw.videodemo.custom.CustomVideoView;

public class FullScreenVideoActivity extends AppCompatActivity {

    private CustomVideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_video);
        mVideoView = (CustomVideoView) findViewById(R.id.custom_video_view);
        mVideoView.setVideoURI(Uri.parse("http://i.snssdk.com/neihan/video/playback/?video_id=840aebabb21d4ed7a27dfd5f993f86e3&quality=360p&line=1&is_gif=0"));
        mVideoView.start();
    }
}
