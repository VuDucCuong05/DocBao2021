package com.example.android45_app_doc_bao_user.dsluy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.SQLHelper;
import com.example.android45_app_doc_bao_trang_chu.AdapterBaiBao;
import com.example.android45_app_doc_bao_trang_chu.BaiBao;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDaLuy extends RecyclerView.Adapter<AdapterDaLuy.Viewhoder> {

    List<BaiBao> list;
    public AdapterDaLuy(List<BaiBao> list) {
        this.list = list;
    }
    IConClickDaLuy iConClickDaLuy;

    public void setiConClickDaLuy(IConClickDaLuy iConClickDaLuy) {
        this.iConClickDaLuy = iConClickDaLuy;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.icon_rcv_da_luy,parent,false);
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
                iConClickDaLuy.ClickLink(baiBao);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(baiBao);
                iConClickDaLuy.ClickId(baiBao.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvIntroduce;
        ImageView imgDelete;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgAnhDaLuy);
            tvTitle = itemView.findViewById(R.id.tvTitleDaLuy);
            tvIntroduce = itemView.findViewById(R.id.tvGioDaLuy);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
