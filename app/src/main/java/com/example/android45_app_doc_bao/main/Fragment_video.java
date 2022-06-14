package com.example.android45_app_doc_bao.main;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.databinding.FragmentVideoBinding;
import com.example.android45_app_doc_bao_rcv_menu.menuto.AdapterBaiBao1;
import com.example.android45_app_doc_bao_video.AdapterBanner;
import com.example.android45_app_doc_bao_video.AdapterVideo;
import com.example.android45_app_doc_bao_video.Banner;
import com.example.android45_app_doc_bao_video.Video;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_video extends Fragment {
    FragmentVideoBinding binding;
    private AdapterBanner adapterBanner;
    private List<Banner> mlist;
    private Timer timer;
    private AdapterVideo adapterVideo;
    private List<Video> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video,container,false);

        mlist = getList();
        adapterBanner = new AdapterBanner(mlist);
        binding.viewPagerBanner.setAdapter(adapterBanner);
        binding.circleBanner.setViewPager(binding.viewPagerBanner);
        adapterBanner.registerDataSetObserver(binding.circleBanner.getDataSetObserver());
        autoSlideImage();



        binding.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                binding.videoview.setVideoPath("android.resource://"+ getActivity().getPackageName()+"/"+R.raw.video2);
//                MediaController mediaController = new MediaController(getActivity());
//                mediaController.setAnchorView(binding.videoview);
//                binding.videoview.setMediaController(mediaController);
//                binding.videoview.requestFocus();
//                binding.videoview.start();
//                binding.imgPlay.setVisibility(View.GONE);

                Uri uri = Uri.parse("https://www.youtube.com/watch?v=-0Xh02OZF3A&list=RD5AQR7KaZEBg&index=2");
                binding.videoview.setVideoURI(uri);
                binding.videoview.start();
            }
        });

        getLifecycle().addObserver(binding.youtubePlayerView);
        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "6PHmGPS6r8A";
                youTubePlayer.loadVideo(videoId, 10);
            }
        });


//        listVideo();
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getBaseContext()
//                ,1,RecyclerView.VERTICAL,false);
//        adapterVideo = new AdapterVideo(list, getActivity());
//        binding.lvVideo.setLayoutManager(layoutManager);
//        binding.lvVideo.setAdapter(adapterVideo);

        return binding.getRoot();
    }
    private void listVideo(){
        list = new ArrayList<>();
        list.add(new Video(R.raw.video1,"Thời sự"));
        list.add(new Video(R.raw.video2,"Thời thiết"));
    }



    private List<Banner> getList(){
        List<Banner> list = new ArrayList<>();
        list.add(new Banner(R.drawable.banner1));
        list.add(new Banner(R.drawable.banner3));
        list.add(new Banner(R.drawable.banner2));
        list.add(new Banner(R.drawable.banner4));
        return list;
    }
    private void autoSlideImage(){
        if(mlist == null || mlist.isEmpty() || binding.viewPagerBanner == null){
            return;
        }
        if(timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = binding.viewPagerBanner.getCurrentItem();
                        int totalItem = mlist.size() - 1;
                        if(currentItem<totalItem){
                            currentItem++;
                            binding.viewPagerBanner.setCurrentItem(currentItem);
                        }else{
                            binding.viewPagerBanner.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }
}
