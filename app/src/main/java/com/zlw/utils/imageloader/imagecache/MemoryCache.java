package com.zlw.utils.imageloader.imagecache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

public class MemoryCache implements IImageCache {

    private LruCache<String, Bitmap> lruCache;
    // 单利模式
    private static MemoryCache memoryCache;

    public static MemoryCache getInstance() {
        if (memoryCache == null) {
            memoryCache = new MemoryCache();
            Log.i("cache", "========重新构造Lru内存缓存!============");
        } else {
            Log.i("cache", " ======使用以构造的Lru内存缓存!============");
        }

        return memoryCache;
    }

    private MemoryCache() {
        initMyCache();
    }

    private void initMyCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4; // 设置缓存为最大内存的1/4;
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    @Override
    public void put(String imageUrl, Bitmap bitmap) {
        if (lruCache.get(imageUrl) == null) {
            lruCache.put(imageUrl, bitmap);
        }
    }

    @Override
    public Bitmap get(String imageUrl) {
        return this.lruCache.get(imageUrl);
    }

}
