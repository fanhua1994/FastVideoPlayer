package com.hengyi.fastvideoplayer.library;

import android.content.Context;

/**
 *
 * 类描述：获取唯一的视频控制器
 *
 * @author Super南仔
 * @time 2016-9-19
 */
public class FastVideoPlayerManage {
    public static FastVideoPlayerManage videoPlayViewManage;
    private FastVideoPlayer videoPlayView;

    private FastVideoPlayerManage() {

    }

    public static FastVideoPlayerManage getSuperManage() {
        if (videoPlayViewManage == null) {
            videoPlayViewManage = new FastVideoPlayerManage();
        }
        return videoPlayViewManage;
    }

    public FastVideoPlayer initialize(Context context) {
        if (videoPlayView == null) {
            videoPlayView = new FastVideoPlayer(context);
        }
        return videoPlayView;
    }
}
