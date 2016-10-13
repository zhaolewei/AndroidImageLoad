package com.zlw.utils.imageloader;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.zlw.utils.imageloader.imagecache.IImageCache;
import com.zlw.utils.imageloader.imagedown.IImageDownload;

/**
 * 图片加载器-策略模式-开闭原则
 * Created by 赵LW on 2016/10/12 0012.
 * </br>
 * 权限：<uses-permission android:name="android.permission.INTERNET"/>
 */
public class ImageLoader {

    private IImageDownload imageDownload;
    private IImageCache imageCache;

    public ImageLoader(IImageDownload imageDownload, IImageCache imageCache) {
        this.imageDownload = imageDownload;
        this.imageCache = imageCache;
    }

    /**
     * 图片加载器
     *
     * @param imageurl
     * @param view
     * @param <T>
     */
    public <T extends ImageView> void loadImage(String imageurl, final ListView lv, T view) {
        view.setTag(imageurl);
        Bitmap bitmap = null;
        if (imageCache != null) {     //空指针检验
            bitmap = imageCache.get(imageurl);
        }
        if (bitmap != null) {
            view.setImageBitmap(bitmap);
            return;
        }


        imageDownload.download(imageurl, new IImageDownload.ImageListener() {
            @Override
            public void onSuccess(String imageurl, Bitmap bitmap) {
                if (imageCache != null)
                    imageCache.put(imageurl, bitmap);
                T v = (T) lv.findViewWithTag(imageurl);
                v.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(String msg) {
                Log.e("imageloader", "图片加载错误：" + msg);
            }
        });
    }
    public <T extends ImageView> void loadImage(String imageurl, final T view) {
        Bitmap bitmap = null;
        if (imageCache != null) {     //空指针检验
            bitmap = imageCache.get(imageurl);
        }
        if (bitmap != null) {
            view.setImageBitmap(bitmap);
            return;
        }


        imageDownload.download(imageurl, new IImageDownload.ImageListener() {
            @Override
            public void onSuccess(String imageurl, Bitmap bitmap) {
                if (imageCache != null)
                    imageCache.put(imageurl, bitmap);
                view.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(String msg) {
                Log.e("imageloader", "图片加载错误：" + msg);
            }
        });
    }

}
