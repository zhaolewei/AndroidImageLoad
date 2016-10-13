# ZLWImageLoader   图片加载框架
 文章地址链接：
 https://github.com/zhaolewei/AndroidImageLoad
 欢迎fork
 
 采用策略模式进行设计，符合开闭原则
 
## 使用方法：
1. 通用方法（三级缓存） HttpDownload+DoubleCatch机制
````java
    ZLWImageLoader.init(context).loadImage(data[position], lv, holder.img); //防止listview图片错位的方法
    ZLWImageLoader.init(context).loadImage(imageurl, ,imageview);  //加载普通Imageview使用
````
2. 扩展方法
*  自定义策略加载Listview中的图片
````java
    ImageLoader imageload = new ImageLoader(imageDownLoadImpl, imageCatchImpl);
    imageload.loadImage(imageurl, lv, imageview);
````
*   加载普通图片
    使用ImageHttpDownLoad
    
````java
    IImageDownload imageDownload = new ImageHttpDownLoad();
    imageDownload.download(imageurl, new IImageDownload.ImageListener() {
                @Override
                public void onSuccess(String imageurl, Bitmap bitmap) {
                //在此可加入缓存策略（IImageCache），此处略去
                    if (bitmap != null)
                       v.setImageBitmap(bitmap);
                }
    
                @Override
                public void onFailure(String msg) {
                    Log.e("imageloader", "图片加载错误：" + msg);
                }
            });
````    

####   imageDownLoadImpl 为 IImageDownload接口的实现类
   
>    IImageDownload.java (接口)
>>    ImageHttpDownLoad （实现类）
    
    imageCatchImpl为 IImageCatch接口的实现类
    接口实现类:   
>    IImageCatch.java
>>    MemoryCatch.java
>>    DiskCatch.java
>>    DoubleCatch.java

##导入方法：
 第一种：复制com.zlw.utils.imageloader到自己的项目中即可
 第二种：将本项目（AS版本）作为类库导入


****
#### 其他图片加载类库
  Glide:[https://github.com/bumptech/glide](https://github.com/bumptech/glide)

  Picasa:[http://picasadownloader.codeplex.com/](http://picasadownloader.codeplex.com/)

 
 

****
****
###其他
>@author: 赵LW
>@email:  739043667@qq.com
>本文链接：[https://github.com/zhaolewei/AndroidImageLoad/](https://github.com/zhaolewei/AndroidImageLoad/)
>日期:    2016.10.13