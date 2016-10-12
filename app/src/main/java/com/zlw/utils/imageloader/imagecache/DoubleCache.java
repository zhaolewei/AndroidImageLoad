package com.zlw.utils.imageloader.imagecache;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class DoubleCache implements IImageCache {

    private MemoryCache memoryCache;
    private DiskCache diskCache;

    public DoubleCache(Context context) {
        memoryCache = MemoryCache.getInstance();
        diskCache = DiskCache.getInstance(context);
    }

    @Override
    public void put(String imageUrl, Bitmap bitmap) {
        Log.i("cache", "DoubleCache 存入缓存");
        memoryCache.put(imageUrl, bitmap);
        diskCache.put(imageUrl, bitmap);
    }

    @Override
    public Bitmap get(String imageUrl) {
        Bitmap bitmap = null;
        bitmap = memoryCache.get(imageUrl);
        if (bitmap != null) {
            Log.i("cache", "DoubleCache 取出缓存from：Memory");
            return bitmap;
        }

        bitmap = diskCache.get(imageUrl);
        if (bitmap != null) {
            Log.i("cache", "DoubleCache 取出缓存from：Disk");
            memoryCache.put(imageUrl, bitmap);
            return bitmap;
        }
        return null;
    }

}
