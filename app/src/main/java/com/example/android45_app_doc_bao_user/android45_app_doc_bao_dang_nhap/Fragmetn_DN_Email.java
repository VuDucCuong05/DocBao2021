package com.example.android45_app_doc_bao_user.android45_app_doc_bao_dang_nhap;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.SQLHelper;
import com.example.android45_app_doc_bao.databinding.FragmentDnTkBinding;
import com.example.android45_app_doc_bao.main.Fragment_user;
import com.example.android45_app_doc_bao_user.User;

import java.util.ArrayList;
import java.util.List;

public class Fragmetn_DN_Email extends Fragment {
    FragmentDnTkBinding binding;
    SQLHelper sqlHelper;
    List<TaiKhoan> taiKhoanList;
    public static Fragmetn_DN_Email newInstance() {

        Bundle args = new Bundle();

        Fragmetn_DN_Email fragment = new Fragmetn_DN_Email();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_dn_tk,container,false);

        binding.imgMuiTenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_Dang_Nhap());
            }
        });
        binding.tvTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_DK_Email());
            }
        });
        sqlHelper = new SQLHelper(getActivity());
        taiKhoanList = new ArrayList<>();
        binding.btDnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gmail = binding.edtNhapEmail.getText().toString().trim();
                String pass = binding.edtNhapMk.getText().toString().trim();
                taiKhoanList = sqlHelper.getAllTaiKhoan();
                if(KiemTraTaiKhoanDn(taiKhoanList,gmail,pass)){
                    getFragment(new Fragment_user());
                      // chuyển tên về
                    Intent intent = new Intent(getActivity(), User.class);
                    String gmailGui = gmail.substring(0,gmail.length()-10);
                    intent.putExtra("ten",gmailGui);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), R.string.tb_gmail_mk_khong_khop, Toast.LENGTH_SHORT).show();
                }
            }
        });

        AnHienMK();
        return  binding.getRoot();
    }

    private void AnHienMK() {
        binding.imgVisi.setVisibility(View.GONE);
        binding.imgVisiOff.setVisibility(View.VISIBLE);
        binding.imgVisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgVisi.setVisibility(View.GONE);
                binding.imgVisiOff.setVisibility(View.VISIBLE);
                binding.edtNhapMk.setTransformationMethod(new PasswordTransformationMethod());

            }
        });
        binding.imgVisiOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgVisi.setVisibility(View.VISIBLE);
                binding.imgVisiOff.setVisibility(View.GONE);
                binding.edtNhapMk.setTransformationMethod(null);

            }
        });
    }

    private void getFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.fragmentDangNhap,fragment).commit();
    }
    private boolean KiemTraTaiKhoanDn(List<TaiKhoan> list,String gmail1,String pass1){
        for(int i = 0; i< list.size();i++){
            if(list.get(i).getGmail().equals(gmail1) == true && list.get(i).getPass().equals(pass1) == true){
                return true;
            }
        }
        return false;

    }
}
