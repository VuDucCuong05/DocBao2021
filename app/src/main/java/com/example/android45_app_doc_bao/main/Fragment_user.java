package com.example.android45_app_doc_bao.main;

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
import com.example.android45_app_doc_bao.databinding.FragmentUserBinding;
import com.example.android45_app_doc_bao_user.android45_app_doc_bao_dang_nhap.DangNhap;
import com.example.android45_app_doc_bao_user.dsluy.ActivityDaLuy;

public class Fragment_user extends Fragment {
    FragmentUserBinding binding;
    public static Fragment_user newInstance() {

        Bundle args = new Bundle();

        Fragment_user fragment = new Fragment_user();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user,container,false);

        binding.tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DangNhap.class);
                startActivity(intent);
            }
        });
        Intent intent = getActivity().getIntent();
        String nhanTen = intent.getStringExtra("ten");
        if(nhanTen != null){
            binding.tvDangNhap.setText(nhanTen);
        }else{
            binding.tvDangNhap.setText("Đăng nhập");
        }
        binding.tvDaDanhDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityDaLuy.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }
}
