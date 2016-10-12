package com.zlw.utils.imageloader.imagedown;

import android.graphics.Bitmap;

/**
 * 图片网络加载接口
 * Created by 赵LW on 2016/10/12 0012.
 */
public abstract class IImageDownload {

    /**
     * 加载完成回调接口
     */
    public interface ImageListener {
        public void onSuccess(String imageurl, Bitmap bitmap);

        public void onFailure(String msg);
    }

    public abstract void download(String imageurl, ImageListener imageListener);


}
