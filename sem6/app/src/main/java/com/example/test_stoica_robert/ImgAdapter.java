package com.example.test_stoica_robert;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class ImgAdapter extends BaseAdapter {
    private List<Bitmap> images;
    private Context context;
    private int ImgLayout;

    public ImgAdapter(List<Bitmap> images, Context context, int imgLayout) {
        this.images = images;
        this.context = context;
        ImgLayout = imgLayout;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(ImgLayout, parent, false);
        }

        ImageView imageView=convertView.findViewById(R.id.ivImg);
        imageView.setImageBitmap(images.get(position));
        return convertView;
    }
}
