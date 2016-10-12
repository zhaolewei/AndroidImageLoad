package com.zlw.utils.imageloader.imagecache;

import android.graphics.Bitmap;

/**
 * 图片缓存接口
 * Created by 赵LW on 2016/10/12 0012.
 */
public interface IImageCache {

    public Bitmap get(String imageurl);

    public void put(String imageurl, Bitmap bitmap);

}
