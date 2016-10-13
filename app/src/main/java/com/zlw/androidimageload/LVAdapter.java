package com.zlw.androidimageload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.zlw.utils.imageloader.ImageLoader;
import com.zlw.utils.imageloader.ZLWImageLoader;
import com.zlw.utils.imageloader.imagecache.DoubleCache;
import com.zlw.utils.imageloader.imagedown.ImageHttpDownLoad;

/**
 * Created by zlw on 2016/10/12 0012.
 */
public class LVAdapter extends BaseAdapter {

    private Context context;
    private ListView lv;
    private String[] data;
    private ImageLoader imageLoader;

    public void setData(String[] data) {
        this.data = data;
    }

    public LVAdapter(Context context, ListView lv) {
        this.lv = lv;
        this.context = context;
        imageLoader = new ImageLoader(new ImageHttpDownLoad(), new DoubleCache(context));
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lv, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.item_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        imageLoader.loadImage(data[position], lv, holder.img);

        ZLWImageLoader.init(context).loadImage(data[position], lv, holder.img);
        return convertView;

    }

    class ViewHolder {
        public ImageView img;
    }
}
