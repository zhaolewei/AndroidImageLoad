package com.zlw.utils.imageloader.imagecache;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.zlw.utils.imageloader.helper.FileHelper;
import com.zlw.utils.imageloader.helper.MD5Util;


public class DiskCache implements IImageCache {

    public String Cache_Path = null;
    private static DiskCache diskCache;

    private DiskCache(Context context) {
        Cache_Path = FileHelper.getDiskCacheDir(context, "images/");
        Log.i("cache", "Cache_Path:" + Cache_Path);
    }

    public static DiskCache getInstance(Context context) {
        if (diskCache == null) {
            diskCache = new DiskCache(context);
        }
        return diskCache;
    }

    @Override
    public void put(String imageUrl, Bitmap bitmap) {
        String md5_imageUrl = MD5Util.md5(imageUrl) + ".png";

        try {
            FileHelper.bitmapToFile(bitmap, Cache_Path, md5_imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Bitmap get(String imageUrl) {

        String md5_imageUrl = MD5Util.md5(imageUrl) + ".png";

        return BitmapFactory.decodeFile(Cache_Path + md5_imageUrl);
    }

}
