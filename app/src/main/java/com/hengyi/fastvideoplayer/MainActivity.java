package com.hengyi.fastvideoplayer;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.hengyi.fastvideoplayer.library.FastVideoPlayer;

public class MainActivity extends AppCompatActivity {
    private FastVideoPlayer videoPlayer;
    private Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoPlayer = findViewById(R.id.fastvideo_player);
        play = findViewById(R.id.play);
        videoPlayer.setLive(false);//是直播还是点播  false为点播
        videoPlayer.setScaleType(FastVideoPlayer.SCALETYPE_FITXY);
        videoPlayer.setTitle("TiDB宣传视频");//设置标题
        videoPlayer.setUrl("https://download.pingcap.com/videos/pingcap-intro-converted.mp4");

        //封面图加载
        Glide.with(this).load("https://download.pingcap.com/images/video-poster.jpg").into(videoPlayer.getCoverImage());

        play.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                videoPlayer.play();
            }
        });
    }

    /**
     * 下面的这几个Activity的生命状态很重要
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (videoPlayer != null) {
            videoPlayer.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoPlayer != null) {
            videoPlayer.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoPlayer != null) {
            videoPlayer.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (videoPlayer != null) {
            videoPlayer.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (videoPlayer != null && videoPlayer.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
