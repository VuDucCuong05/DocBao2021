package com.example.android45_app_doc_bao_video;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.databinding.IconVideoBinding;
import com.example.android45_app_doc_bao_trang_chu.AdapterBaiBao;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.Viewhoder> {
    List<Video> list;
    private Context context;
    public AdapterVideo(List<Video> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.icon_rcv_noi_dung,parent,false);
        AdapterVideo.Viewhoder viewhoder = new Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Video video = list.get(position);
        holder.ten.setText(video.getTen());
        holder.video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "6PHmGPS6r8A";
                youTubePlayer.loadVideo(videoId, 10);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        YouTubePlayerView video;
        TextView ten;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            video = itemView.findViewById(R.id.video);
            ten = itemView.findViewById(R.id.tvTitle);
        }
    }
}
