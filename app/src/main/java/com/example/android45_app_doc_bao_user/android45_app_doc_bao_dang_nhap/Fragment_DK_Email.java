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
import com.example.android45_app_doc_bao.databinding.FragmentTaoTkBinding;
import com.example.android45_app_doc_bao.main.Fragment_user;
import com.example.android45_app_doc_bao_user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragment_DK_Email extends Fragment {
    FragmentTaoTkBinding binding;
    SQLHelper sqlHelper;
    List<TaiKhoan> taiKhoanList;
    public static Fragment_DK_Email newInstance() {

        Bundle args = new Bundle();

        Fragment_DK_Email fragment = new Fragment_DK_Email();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tao_tk,container,false);
        sqlHelper = new SQLHelper(getActivity());
        taiKhoanList = new ArrayList<>();
        binding.imgMuiTenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragmetn_DN_Email());
            }
        });
        binding.btTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nhapgmail = binding.edtNhapEmail.getText().toString().trim();
                String Nhappass = binding.edtNhapMk.getText().toString().trim();
                String Nhappass1 = binding.edtNhapMk1.getText().toString().trim();
                taiKhoanList = sqlHelper.getAllTaiKhoan();
                if(KiemTraGmail(Nhapgmail,Nhappass,Nhappass1) == true){
                    if(KiemTraTonTaiGmail(taiKhoanList,Nhapgmail) == true){
                        Toast.makeText(getActivity(),R.string.tb_ton_tai_gmail, Toast.LENGTH_SHORT).show();
                    }else{
                        sqlHelper.addTaiKhoan(new TaiKhoan(1,Nhapgmail,Nhappass));
                        getFragment(new Fragment_user());
                        Intent intent = new Intent(getActivity(), User.class);
                        String gmailGui = Nhapgmail.substring(0,Nhapgmail.length()-10);
                        intent.putExtra("ten",gmailGui);
                        startActivity(intent);
                    }
                }
            }
        });
        binding.tvTaoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragmetn_DN_Email());
            }
        });
        // custom edt https://openplanning.net/12691/android-textinputlayout

        // ẩn hiện mật khẩu
        AnHienMK();
        return  binding.getRoot();
    }

    private void AnHienMK() {
        binding.imgVisi1.setVisibility(View.GONE);
        binding.imgVisi.setVisibility(View.GONE);
        binding.imgVisiOff.setVisibility(View.VISIBLE);
        binding.imgVisiOff1.setVisibility(View.VISIBLE);
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
        binding.imgVisi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgVisi1.setVisibility(View.GONE);
                binding.imgVisiOff1.setVisibility(View.VISIBLE);
                binding.edtNhapMk1.setTransformationMethod(new PasswordTransformationMethod());

            }
        });
        binding.imgVisiOff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imgVisi1.setVisibility(View.VISIBLE);
                binding.imgVisiOff1.setVisibility(View.GONE);
                binding.edtNhapMk1.setTransformationMethod(null);

            }
        });
    }
    private void getFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.fragmentDangNhap,fragment).commit();
    }
    public boolean KiemTraGmail(String gmail,String pass,String pass1){
        if(gmail == "" || pass == "" || pass1 ==""){
            Toast.makeText(getActivity(), R.string.tb_nhap_day_du, Toast.LENGTH_SHORT).show();
        }else{
            String dkGmail = "^[a-zA-z]+[a-zA-z0-9]*@+gmail.com$";
            String dkPass = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
            Pattern pattern = Pattern.compile(dkGmail);
            Matcher matcher = pattern.matcher(gmail);
            if(matcher.find()){
                Pattern pattern1 = Pattern.compile(dkPass);
                Matcher matcher1 = pattern1.matcher(pass);
                if(matcher1.find()){
                    if(pass.equals(pass1) == true){
                        return true;
                    }else{
                        Toast.makeText(getActivity(),R.string.tb_pass_kk, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),R.string.tb_pass, Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getActivity(), R.string.tb_gmail, Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
    public boolean KiemTraTonTaiGmail(List<TaiKhoan> list,String ten){
        for(int i = 0; i< list.size();i++){
            if(list.get(i).getGmail().equals(ten) == true){
                return true;
            }
        }
        return false;
    }

}
