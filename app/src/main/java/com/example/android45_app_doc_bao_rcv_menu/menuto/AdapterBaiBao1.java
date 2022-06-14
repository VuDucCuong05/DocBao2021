package com.example.android45_app_doc_bao_rcv_menu.menuto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao_trang_chu.BaiBao;
import com.example.android45_app_doc_bao_trang_chu.IConClickRCVBaiBao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdapterBaiBao1 extends RecyclerView.Adapter<AdapterBaiBao1.Viewhoder> {
    List<BaiBao> list;
    IConClickRCVBaiBao iConClickRCVBaiBao;
    public AdapterBaiBao1(List<BaiBao> list) {
        this.list = list;
    }

    public void setiConClickRCVBaiBao(IConClickRCVBaiBao iConClickRCVBaiBao) {
        this.iConClickRCVBaiBao = iConClickRCVBaiBao;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.icon_rcv_noi_dung,parent,false);
        Viewhoder viewhoder = new Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        BaiBao baiBao = list.get(position);
        holder.tvTitle.setText(baiBao.getTitel());
        Picasso.get()
                .load(baiBao.getImage())
                .into(holder.imageView);
        holder.tvIntroduce.setText(baiBao.getIntroduce());
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iConClickRCVBaiBao.ClickLink(baiBao);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    // tìm kiếm
    public void filterList(ArrayList<BaiBao> listm){
        list = listm;
        notifyDataSetChanged();
    }
    public class Viewhoder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvIntroduce;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgAnh);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvIntroduce = itemView.findViewById(R.id.tvChuThich);
        }
    }
}
