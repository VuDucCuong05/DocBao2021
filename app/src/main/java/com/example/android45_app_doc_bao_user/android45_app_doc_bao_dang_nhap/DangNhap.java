package com.example.android45_app_doc_bao_user.android45_app_doc_bao_dang_nhap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.databinding.ActivityDangNhapBinding;


public class DangNhap extends AppCompatActivity {

    ActivityDangNhapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_dang_nhap);
        CreateFragment(new Fragment_Dang_Nhap());
    }

    private void CreateFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDangNhap,fragment).commit();
    }


}