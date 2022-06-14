package com.example.android45_app_doc_bao_gioi_thieu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.databinding.ActivityGioiThieuBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import me.relex.circleindicator.CircleIndicator;

public class ActivityGioiThieu extends AppCompatActivity {

    ActivityGioiThieuBinding binding;
    AdapterViewPage adapterViewPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_gioi_thieu);

        adapterViewPage = new AdapterViewPage(this);
        binding.viewPager2.setAdapter(adapterViewPage);
        binding.circle3.setViewPager(binding.viewPager2);
        binding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.viewPager2.setCurrentItem(2);
                //binding.imgMuiTenGioiThieu.setVisibility(View.GONE);

            }
        });
        binding.imgMuiTenGioiThieu.setVisibility(View.GONE);

//        binding.imgMuiTenGioiThieu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(binding.viewPager2.getCurrentItem()<2) {
//                    binding.viewPager2.setCurrentItem(binding.viewPager2.getCurrentItem() + 1);
//                }
//                if(binding.viewPager2.getCurrentItem()==2) {
//                    binding.imgMuiTenGioiThieu.setVisibility(View.GONE);
//                }else{
//                    binding.imgMuiTenGioiThieu.setVisibility(View.VISIBLE);
//                }
//
//            }
//        });
    }
}