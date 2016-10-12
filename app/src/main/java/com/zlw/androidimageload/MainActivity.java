package com.zlw.androidimageload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.zlw.utils.imageloader.ImageLoader;
import com.zlw.utils.imageloader.imagedown.ImageHttpDownLoad;

public class MainActivity extends AppCompatActivity {



    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);

        LVAdapter adapter = new LVAdapter(getApplicationContext(), lv);
        adapter.setData(new String[]{
                "http://img1.imgtn.bdimg.com/it/u=1794894692,1423685501&fm=21&gp=0.jpg",
                "http://img0.imgtn.bdimg.com/it/u=938096994,3074232342&fm=21&gp=0.jpg",
                "http://img4.duitang.com/uploads/item/201208/25/20120825230802_2v8wi.jpeg",
                "http://pic.58pic.com/58pic/13/40/15/74W58PICfa7_1024.jpg",
                "http://pic27.nipic.com/20130315/9022728_123431241178_2.jpg",
                "http://pic56.nipic.com/file/20141227/19674963_215052431000_2.jpg",
                "http://dl.bizhi.sogou.com/images/2012/01/19/191337.png",
                "http://i1.hexunimg.cn/2014-08-15/167580248.jpg",
                "http://img01.taopic.com/151227/234973-15122G5550795.jpg",
                "http://r1.ykimg.com/054104085274F02D6A0A40636C798A21",
                "http://c.hiphotos.baidu.com/image/h%3D200/sign=d1665a7532d12f2ed105a9607fc3d5ff/d0c8a786c9177f3ecd83c7af74cf3bc79e3d56d8.jpg",
                "http://img6.faloo.com/Picture/0x0/0/183/183390.jpg",
                "http://img2.3lian.com/2014/c6/18/d/61.jpg"
        });
        lv.setAdapter(adapter);
    }
}
