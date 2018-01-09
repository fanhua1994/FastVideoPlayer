package com.hengyi.fastvideoplayer;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.hengyi.fastvideoplayer.library.FastVideoPlayer;

public class MainActivity extends AppCompatActivity {
    private FastVideoPlayer superPlayer;
    private Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        superPlayer = findViewById(R.id.super_player);
        play = findViewById(R.id.play);
        superPlayer.setLive(false);//是直播还是点播  false为点播
        superPlayer.setScaleType(FastVideoPlayer.SCALETYPE_FITXY);
        superPlayer.setTitle("TiDB宣传视频");//设置标题
        superPlayer.setUrl("https://download.pingcap.com/videos/pingcap-intro-converted.mp4");

        //封面图加载
        Glide.with(this).load("https://download.pingcap.com/images/video-poster.jpg").into(superPlayer.getCoverImage());

        play.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                superPlayer.play();
            }
        });
    }

    /**
     * 下面的这几个Activity的生命状态很重要
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (superPlayer != null) {
            superPlayer.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (superPlayer != null) {
            superPlayer.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (superPlayer != null) {
            superPlayer.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (superPlayer != null) {
            superPlayer.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (superPlayer != null && superPlayer.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
