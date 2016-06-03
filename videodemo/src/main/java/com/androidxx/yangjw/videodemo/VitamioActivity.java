package com.androidxx.yangjw.videodemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VitamioActivity extends AppCompatActivity {

    private VideoView mVitamioView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_vitamio);
        mVitamioView = (VideoView) findViewById(R.id.vitamio_view);
        Uri uri = Uri.parse("http://i.snssdk.com/neihan/video/playback/?video_id=840aebabb21d4ed7a27dfd5f993f86e3&quality=360p&line=1&is_gif=0");
        mVitamioView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        mVitamioView.setMediaController(mediaController);
        mVitamioView.start();
    }
}
