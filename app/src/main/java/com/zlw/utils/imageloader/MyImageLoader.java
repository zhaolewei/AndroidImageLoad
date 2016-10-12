package com.zlw.utils.imageloader;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ListView;

import com.zlw.utils.imageloader.imagecache.DoubleCache;
import com.zlw.utils.imageloader.imagecache.IImageCache;
import com.zlw.utils.imageloader.imagedown.IImageDownload;
import com.zlw.utils.imageloader.imagedown.ImageHttpDownLoad;

/**
 * Created by zlw on 2016/10/12 0012.
 */
public class MyImageLoader {

    private static ImageLoader imageLoader; //单例模式

    public static ImageLoader init(Context context) {
        if (imageLoader == null) {
            imageLoader = new ImageLoader(new ImageHttpDownLoad(), new DoubleCache(context));
        }
        return imageLoader;
    }

}

