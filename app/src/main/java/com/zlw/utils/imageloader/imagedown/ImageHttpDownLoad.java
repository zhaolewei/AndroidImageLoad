package com.zlw.utils.imageloader.imagedown;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zlw on 2016/10/12 0012.
 */
public class ImageHttpDownLoad extends IImageDownload {

    // 下载图片线程池
    private ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * 下载实现方法
     *
     * @param imageurl      图片地址
     * @param imageListener 下载回调处理
     */
    @Override
    public void download(final String imageurl, final ImageListener imageListener) {
        if (imageurl == null || imageurl.equals("")) {
            imageListener.onFailure("imageurl 不能为空");
            return;
        }
        this.threadPool.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                InputStream is = null;
                try {
                    URL url = new URL(imageurl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    is = new BufferedInputStream(connection.getInputStream());
                    bitmap = BitmapFactory.decodeStream(is);
                    connection.disconnect();
                } catch (Exception e) {
                    imageListener.onFailure("图片下载异常");
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                            is = null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                imageListener.onSuccess(imageurl, bitmap);
            }
        });


    }
}
