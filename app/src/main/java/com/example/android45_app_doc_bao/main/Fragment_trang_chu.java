package com.example.android45_app_doc_bao.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.databinding.FragmentTrangChuBinding;
import com.example.android45_app_doc_bao_rcv_menu.AdapterRcvMenu;
import com.example.android45_app_doc_bao_rcv_menu.IConClickRcvMenu;
import com.example.android45_app_doc_bao_rcv_menu.ThuMuc;
import com.example.android45_app_doc_bao_rcv_menu.menuto.MenuTo;
import com.example.android45_app_doc_bao_retrofit.APIClientlpm;
import com.example.android45_app_doc_bao_retrofit.ThuMucRetrofit;
import com.example.android45_app_doc_bao_trang_chu.Adapterviewpager;
import com.example.android45_app_doc_bao_trang_chu.TrangChu;
import com.example.android45_app_doc_bao_user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_trang_chu extends Fragment implements Serializable {
    FragmentTrangChuBinding binding;
    AdapterRcvMenu adapterRcvMenu;
    ArrayList<ThuMuc> list;
    List<ThuMucRetrofit> listThuMucRetrofits;
    int color = Color.parseColor("#9E2651");
    int den = Color.parseColor("#CAC7C8");
    public String getLink() {
        return link;
    }
    private String link;

    public static Fragment_trang_chu newInstance() {
        Bundle args = new Bundle();
        Fragment_trang_chu fragment = new Fragment_trang_chu();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_trang_chu,container,false);
        binding.imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),User.class);
                startActivity(intent);
            }
        });
        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MenuTo.class);
                startActivity(intent);
            }
        });
        addListThuMuc();
        // view par2
        Adapterviewpager adapterviewpager = new Adapterviewpager(this);
        binding.fl.setAdapter(adapterviewpager);
        binding.fl.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        chuyen1();
                        break;
                    case 1:
                        chuyen2();
                        break;
                    case 2:
                        chuyen3();
                        break;
                    case 3:
                        chuyen4();
                        break;
                    case 4:
                        chuyen5();
                        break;
                    default:
                        chuyen1();
                }
                super.onPageSelected(position);
            }
        });
        chuyenMenu();
//        CreateRcvMenu();
//        chuyenMenuAdapter();
        return  binding.getRoot();
    }
    private void addListThuMuc(){
        list = new ArrayList<>();
        list.add(new ThuMuc(0,"Trang Chủ",R.drawable.trangchu,"https://vnexpress.net/rss/tin-moi-nhat.rss"));
        list.add(new ThuMuc(1,"Thời Sự",R.drawable.thoisu,"https://vnexpress.net/rss/thoi-su.rss"));
        list.add(new ThuMuc(2,"Thế Giới",R.drawable.thegioi,"https://vnexpress.net/rss/the-gioi.rss"));
        list.add(new ThuMuc(3,"Kinh doanh",R.drawable.kinhdoanh,"https://vnexpress.net/rss/kinh-doanh.rss"));
        list.add(new ThuMuc(4,"Startup",R.drawable.thethao,"https://vnexpress.net/rss/tin-noi-bat.rss"));
    }
    private void getTV(){
        binding.tv1.setText(list.get(0).getTen());
        binding.tv2.setText(list.get(1).getTen());
        binding.tv3.setText(list.get(2).getTen());
        binding.tv4.setText(list.get(3).getTen());
        binding.tv5.setText(list.get(4).getTen());
    }
    private  void chuyen1(){
        getTV();
        binding.tv1.setTextColor(color);
        binding.tv2.setTextColor(den);
        binding.tv3.setTextColor(den);
        binding.tv4.setTextColor(den);
        binding.tv5.setTextColor(den);
    }
    private  void chuyen2(){
        getTV();
        binding.tv2.setTextColor(color);
        binding.tv1.setTextColor(den);
        binding.tv3.setTextColor(den);
        binding.tv4.setTextColor(den);
        binding.tv5.setTextColor(den);
    }
    private  void chuyen3(){
        getTV();
        binding.tv3.setTextColor(color);
        binding.tv1.setTextColor(den);
        binding.tv2.setTextColor(den);
        binding.tv4.setTextColor(den);
        binding.tv5.setTextColor(den);
    }
    private  void chuyen4(){
        getTV();
        binding.tv4.setTextColor(color);
        binding.tv1.setTextColor(den);
        binding.tv3.setTextColor(den);
        binding.tv2.setTextColor(den);
        binding.tv5.setTextColor(den);
    }
    private  void chuyen5(){
        getTV();
        binding.tv5.setTextColor(color);
        binding.tv1.setTextColor(den);
        binding.tv3.setTextColor(den);
        binding.tv4.setTextColor(den);
        binding.tv2.setTextColor(den);
    }
    // từng nút
    private void chuyenMenu(){
        binding.tv1.setText(list.get(0).getTen());
        binding.tv1.setTextColor(color);
        binding.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               binding.fl.setCurrentItem(0);
            }
        });
        binding.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.fl.setCurrentItem(1);
            }
        });
        binding.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.fl.setCurrentItem(2);
            }
        });
        binding.tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.fl.setCurrentItem(3);
            }
        });
        binding.tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.fl.setCurrentItem(4);
            }
        });
    }
    private void getFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.fl,fragment).commit();
    }
//    private  void CreateRcvMenu(){
//        <androidx.recyclerview.widget.RecyclerView
//        android:background="@drawable/custom_trang_chu"
//        android:id="@+id/rcvMenu"
//        android:layout_width="match_parent"
//        android:layout_height="0dp"
//        android:layout_weight="0.6" />
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),
//                1,RecyclerView.HORIZONTAL,false);
//        adapterRcvMenu = new AdapterRcvMenu(list);
//        binding.rcvMenu.setLayoutManager(layoutManager);
//        binding.rcvMenu.setAdapter(adapterRcvMenu);
//    }
private void chuyenMenuAdapter(){
    adapterRcvMenu.setiConClickRcvMenu(new IConClickRcvMenu() {
        @Override
        public void ClickAll(int id) {
            if(id ==0){
                binding.fl.setCurrentItem(0);
            }else if(id ==1){
                binding.fl.setCurrentItem(1);
            }else if(id ==2){
                binding.fl.setCurrentItem(2);
            }else if(id ==3){
                binding.fl.setCurrentItem(3);
            }else if(id ==4){
                binding.fl.setCurrentItem(4);
            }
        }
    });
}

}
