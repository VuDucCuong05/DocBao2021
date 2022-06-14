package com.example.android45_app_doc_bao_rcv_menu;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.databinding.IconRcvMenuBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterRcvMenu extends RecyclerView.Adapter<AdapterRcvMenu.Viewhoder> {
    ArrayList<ThuMuc> list;
    IconRcvMenuBinding bing;
    public AdapterRcvMenu(ArrayList<ThuMuc> list) {
        this.list = list;
    }
    IConClickRcvMenu iConClickRcvMenu;

    public void setiConClickRcvMenu(IConClickRcvMenu iConClickRcvMenu) {
        this.iConClickRcvMenu = iConClickRcvMenu;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.icon_rcv_menu,parent,false);
        Viewhoder viewhoder = new Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        ThuMuc thuMuc = list.get(position);
        holder.tv.setText(thuMuc.getTen());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.tv.setBackgroundColor(Color.parseColor("#F41D0D"));
                iConClickRcvMenu.ClickAll(thuMuc.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        TextView tv;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvRcvMenu);
        }
    }
}
