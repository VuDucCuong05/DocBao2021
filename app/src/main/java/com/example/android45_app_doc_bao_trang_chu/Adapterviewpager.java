package com.example.android45_app_doc_bao_trang_chu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.main.Fragment_trang_chu;
import com.example.android45_app_doc_bao_rcv_menu.ThuMuc;
import com.example.android45_app_doc_bao_trang_chu.TrangChu;

import java.util.ArrayList;

public class Adapterviewpager extends FragmentStateAdapter {

    public Adapterviewpager(@NonNull Fragment_trang_chu fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                TrangChu trangChu = new TrangChu("https://vnexpress.net/rss/tin-moi-nhat.rss");
                return trangChu.newInstance();
            case 1:
                TrangChu trangChu1 = new TrangChu("https://vnexpress.net/rss/thoi-su.rss");
                return trangChu1.newInstance();
            case 2:
                TrangChu trangChu2 = new TrangChu("https://vnexpress.net/rss/the-gioi.rss");
                return trangChu2.newInstance();
            case 3:
                TrangChu trangChu3 = new TrangChu("https://vnexpress.net/rss/kinh-doanh.rss");
                return trangChu3.newInstance();
            case 4:
                TrangChu trangChu4 = new TrangChu("https://vnexpress.net/rss/tin-noi-bat.rss");
                return trangChu4.newInstance();
            default:
                TrangChu trangChu5 = new TrangChu("https://vnexpress.net/rss/tin-moi-nhat.rss");
                return trangChu5.newInstance();
        }
    }
    @Override
    public int getItemCount() {
        return 5;
    }
}
