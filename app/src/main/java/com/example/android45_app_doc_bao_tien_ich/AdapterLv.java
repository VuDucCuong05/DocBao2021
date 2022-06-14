package com.example.android45_app_doc_bao_tien_ich;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android45_app_doc_bao.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterLv extends BaseAdapter {
    Context context;
    ArrayList<ThoiTiet> list;

    public AdapterLv(Context context, ArrayList<ThoiTiet> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.icon_lv,null);
        TextView tvDay  = convertView.findViewById(R.id.tvNgay);
        TextView tvStatus  = convertView.findViewById(R.id.tvTrangThat2);
        TextView tvMaxTemp  = convertView.findViewById(R.id.tvMaxTemp);
        TextView tvMinTemp  = convertView.findViewById(R.id.tvMinTemp);
        ImageView image = convertView.findViewById(R.id.imgTrangThai);

        ThoiTiet thoiTiet = list.get(position);
        tvDay.setText(thoiTiet.getDay());
        tvStatus.setText(thoiTiet.getStatus());
        tvMaxTemp.setText(thoiTiet.getMaxTemp());
        tvMinTemp.setText(thoiTiet.getMinTemp());
        Picasso.get()
                .load("http://openweathermap.org/img/w/"+thoiTiet.getImage()+".png")
                .into(image);
        return convertView;
    }
}
