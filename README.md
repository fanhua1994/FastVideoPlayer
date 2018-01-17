# FastVideoPlayer
基于ijkplayer开发万能播放器，支持点播、直播播放。

![](https://github.com/fanhua1994/FastVideoPlayer/blob/master/image/B43F89B4D9B5AE374BB9AE8A966D2006.png?raw=true)
![](https://github.com/fanhua1994/FastVideoPlayer/blob/master/image/16CDF9C1CDBD0E54F934E532C8351A5B.jpg?raw=true)


# 如何引用
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```
dependencies {
        compile 'com.github.fanhua1994:FastVideoPlayer:1.0.3'
}
```

# demo演示
AndroidMainist.xml
```
 <activity android:name=".MainActivity"
    android:configChanges="orientation|screenSize|keyboardHidden">
</activity>
```

```
<com.hengyi.fastvideoplayer.library.FastVideoPlayer
    android:id="@+id/fastvideo_player"
    android:layout_width="match_parent"
    android:layout_height="200dp"></com.hengyi.fastvideoplayer.library.FastVideoPlayer>
```

```
videoPlayer = findViewById(R.id.fastvideo_player);
videoPlayer.setLive(false);//是直播还是点播  false为点播
videoPlayer.setScaleType(FastVideoPlayer.SCALETYPE_FITXY);
videoPlayer.setTitle("TiDB宣传视频");//设置标题
videoPlayer.setUrl("https://download.pingcap.com/videos/pingcap-intro-converted.mp4");
videoPlayer.play();//自动播放
//封面图加载
Glide.with(this).load("https://download.pingcap.com/images/video-poster.jpg").into(videoPlayer.getCoverImage());
```
屏幕监听
```
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
```
```
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
```
