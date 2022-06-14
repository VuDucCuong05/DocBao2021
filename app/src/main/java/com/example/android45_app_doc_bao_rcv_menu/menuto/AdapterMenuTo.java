package com.example.android45_app_doc_bao_rcv_menu.menuto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao_rcv_menu.IConClickRcvMenu;
import com.example.android45_app_doc_bao_rcv_menu.ThuMuc;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMenuTo extends RecyclerView.Adapter<AdapterMenuTo.Viewhoder> {
    ArrayList<ThuMuc> list;
    IConClickRcvMenu iConClickRcvMenu;

    public AdapterMenuTo(ArrayList<ThuMuc> list) {
        this.list = list;
    }

    public void setiConClickRcvMenu(IConClickRcvMenu iConClickRcvMenu) {
        this.iConClickRcvMenu = iConClickRcvMenu;
    }

    @NonNull
    @Override
    public AdapterMenuTo.Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.icon_rcv_menu_to,parent,false);
        Viewhoder viewhoder = new Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMenuTo.Viewhoder holder, int position) {
        ThuMuc thuMuc = list.get(position);
//        Picasso.get()
//                .load(thuMuc.getAnh())
//                .into(holder.imgMenuTo);
        holder.imgMenuTo.setImageResource(thuMuc.getAnh());
        holder.tvTen.setText(thuMuc.getTen());

        holder.tvTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iConClickRcvMenu.ClickAll(thuMuc.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        ImageView imgMenuTo;
        TextView tvTen;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            imgMenuTo = itemView.findViewById(R.id.imgAnhMenuTo);
            tvTen = itemView.findViewById(R.id.tvTenMenuTo);
        }
    }
}
