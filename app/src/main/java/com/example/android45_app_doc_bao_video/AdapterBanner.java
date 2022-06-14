package com.example.android45_app_doc_bao_video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.main.Fragment_video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterBanner extends PagerAdapter {
    private List<Banner> list;

    public AdapterBanner(List<Banner> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner,container,false);
        ImageView imageView = view.findViewById(R.id.imgBanner);
        Banner banner = list.get(position);
        Picasso.get()
                .load(banner.getAnh())
                .into(imageView);
        //Glide.with(this).load(banner.getAnh()).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
