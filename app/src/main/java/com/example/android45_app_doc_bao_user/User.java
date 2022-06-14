package com.example.android45_app_doc_bao_user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android45_app_doc_bao.MainActivity;
import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.SQLHelper;
import com.example.android45_app_doc_bao.databinding.ActivityUserBinding;
import com.example.android45_app_doc_bao_user.android45_app_doc_bao_dang_nhap.DangNhap;
import com.example.android45_app_doc_bao_user.android45_app_doc_bao_dang_nhap.TaiKhoan;
import com.example.android45_app_doc_bao_user.dsdd.ActivityDaDocGanDay;
import com.example.android45_app_doc_bao_user.dsluy.ActivityDaLuy;

import java.util.ArrayList;
import java.util.List;

public class User extends AppCompatActivity {

    SQLHelper sqlHelper;
    ActivityUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user);

        binding.tvDaDanhDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User.this,ActivityDaLuy.class);
                startActivity(intent);
            }
        });
        binding.tvDaDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User.this, ActivityDaDocGanDay.class);
                startActivity(intent);
            }
        });
        binding.imgMuiTenUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_input,R.anim.anim_out);
            }
        });
        binding.tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User.this, DangNhap.class);
                startActivity(intent);
            }
        });
        sqlHelper = new SQLHelper(this);
        List<TaiKhoan> list =new ArrayList<>();
        list = sqlHelper.getAllTaiKhoan();
//        Intent intent = getIntent();
//        String nhanTen = intent.getStringExtra("ten");
        if(list.size()>0){
            String ten = list.get(0).getGmail();
            String tenCat = ten.substring(0,ten.length()-10);
            binding.tvDangNhap.setText(tenCat);
            binding.tvDangXuat.setVisibility(View.VISIBLE);
            binding.imgUser.setVisibility(View.VISIBLE);

        }else{
            binding.tvDangNhap.setText("Đăng nhập");
            binding.tvDangXuat.setVisibility(View.GONE);
            binding.imgUser.setVisibility(View.GONE);
        }
        binding.tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDialog();
            }
        });

    }

    private void CreateDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(User.this);
        dialog.setTitle("Thoát");
        dialog.setMessage("Bạn muốn đăng xuất!");
        dialog.setIcon(android.R.drawable.ic_dialog_info);
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                binding.imgUser.setVisibility(View.GONE);
                binding.tvDangNhap.setText("Đăng nhập");
                binding.tvDangXuat.setVisibility(View.GONE);
            }
        });
        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.create().show();
    }
}