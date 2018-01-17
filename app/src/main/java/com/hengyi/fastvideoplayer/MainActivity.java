package com.hengyi.fastvideoplayer;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hengyi.fastvideoplayer.library.FastVideoPlayer;
import com.hengyi.fastvideoplayer.library.listener.FastVideoPlayerScreenListener;

public class MainActivity extends AppCompatActivity {
    private FastVideoPlayer videoPlayer;
    private Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoPlayer = findViewById(R.id.fastvideo_player);
        play = findViewById(R.id.play);
        videoPlayer.setLive(true);//是直播还是点播  false为点播
        videoPlayer.setScaleType(FastVideoPlayer.SCALETYPE_FITXY);
        videoPlayer.setTitle("天猫精灵宣传视频");//设置标题
        videoPlayer.setUrl("http://dlhls.cdn.zhanqi.tv/zqlive/49427_jmACJ.m3u8");
        //封面图加载
        Glide.with(this).load("https://bot.tmall.com/guide/img/guide1-bg760.png").into(videoPlayer.getCoverImage());

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                videoPlayer.play();
            }
        });

        videoPlayer.setScreenListener(new FastVideoPlayerScreenListener() {
            @Override
            public void onFullScreen() {
                Toast.makeText(MainActivity.this,"进入全屏",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSmallScreen() {
                Toast.makeText(MainActivity.this,"进入小屏",Toast.LENGTH_SHORT).show();
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
