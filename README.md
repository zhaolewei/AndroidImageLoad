# ZLWImageLoader   图片加载框架
 文章地址链接：
 https://github.com/zhaolewei/AndroidImageLoad
 欢迎fork
 
 采用策略模式进行设计，符合开闭原则
 
## 使用方法：
1. 通用方法（三级缓存） HttpDownload+DoubleCatch机制
````java
    ZLWImageLoader.init(context).loadImage(data[position], lv, holder.img);
````
2. 扩展方法   
````java
    ImageLoader imageload = new ImageLoader(imageDownLoadImpl, imageCatchImpl);
    imageload.loadImage(data[position], lv, holder.img);
````
    imageDownLoadImpl 为 IImageDownload接口的实现类
    接口实现类:   
>    IImageDownload.java
>>    ImageHttpDownLoad
    
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

 
 
