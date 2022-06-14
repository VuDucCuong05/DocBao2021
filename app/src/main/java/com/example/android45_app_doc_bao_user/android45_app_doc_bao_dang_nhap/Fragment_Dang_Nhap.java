package com.example.android45_app_doc_bao_user.android45_app_doc_bao_dang_nhap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.databinding.FragmentDangNhapBinding;
import com.example.android45_app_doc_bao.main.Fragment_user;
import com.example.android45_app_doc_bao_user.User;

public class Fragment_Dang_Nhap extends Fragment {
    FragmentDangNhapBinding binding;

    public static Fragment_Dang_Nhap newInstance() {
        
        Bundle args = new Bundle();
        
        Fragment_Dang_Nhap fragment = new Fragment_Dang_Nhap();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_dang_nhap,container,false);
        binding.imgMuiTenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),User.class);
                startActivity(intent);
            }
        });
        binding.tvDnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragmetn_DN_Email());
            }
        });
        binding.tvTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_DK_Email());
            }
        });
        return  binding.getRoot();

    }
    private void getFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.fragmentDangNhap,fragment).commit();
    }
}
