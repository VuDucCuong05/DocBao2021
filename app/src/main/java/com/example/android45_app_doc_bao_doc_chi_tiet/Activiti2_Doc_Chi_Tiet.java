package com.example.android45_app_doc_bao_doc_chi_tiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android45_app_doc_bao.MainActivity;
import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.SQLHelper;
import com.example.android45_app_doc_bao.databinding.ActivityActiviti2DocChiTietBinding;
import com.example.android45_app_doc_bao_trang_chu.BaiBao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Activiti2_Doc_Chi_Tiet extends AppCompatActivity {

    ActivityActiviti2DocChiTietBinding binding;
    SQLHelper sqlHelper;
    Boolean coden = false;
    int mauTrang = Color.parseColor("#FFFFFFFF");
    int mauDen = Color.parseColor("#FF000000");
    int mauCam = Color.parseColor("#FF9800");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_activiti2_doc_chi_tiet);
        sqlHelper = new SQLHelper(this);

        // nhận dữ liệu
        BaiBao baiBao = (BaiBao) getIntent().getExtras().get("baibaoC");
        sqlHelper.addBaibaoDaDoc1(baiBao);
        binding.imgMuiTenDocChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Activiti2_Doc_Chi_Tiet.this, MainActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.anim_input,R.anim.anim_out);
            }
        });

         //sự kiện tăng giảm kích thức ( dialog)
        binding.imgToNhoDocChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moDialog(Gravity.BOTTOM);
            }
        });
        // sự lưu bài báo ( chuyển qua lại các activi không đc load lại fragment
        if(baiBao.getLuy().equals("false") == true){
            binding.imgLuyDocChiTiet.setVisibility(View.VISIBLE);
            binding.imgBoLuyDocChiTiet.setVisibility(View.GONE);
        }else{
            binding.imgLuyDocChiTiet.setVisibility(View.GONE);
            binding.imgBoLuyDocChiTiet.setVisibility(View.VISIBLE);
        }
        binding.imgLuyDocChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Đã thêm vào ds", Toast.LENGTH_SHORT).show();
                baiBao.setLuy("true");
                sqlHelper.addBaibao(baiBao);
                binding.imgLuyDocChiTiet.setVisibility(View.GONE);
                binding.imgBoLuyDocChiTiet.setVisibility(View.VISIBLE);
            }
        });
        binding.imgBoLuyDocChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Đã xóa khỏi danh sách", Toast.LENGTH_SHORT).show();
                baiBao.setLuy("false");
                sqlHelper.onDeleteBaiBao(baiBao.getId());
                binding.imgLuyDocChiTiet.setVisibility(View.VISIBLE);
                binding.imgBoLuyDocChiTiet.setVisibility(View.GONE);
            }
        });

        binding.imgLinkDocChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Đã copy link", Toast.LENGTH_SHORT).show();
                String link = baiBao.getLink();
            }
        });
        // đọc jsoup
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> listNd = new ArrayList<>();
                StringBuffer buffer = new StringBuffer();
                String TheLoai = "";
                String Date = "";
                String Title = "";
                String Description = "";
                try {
                    Document document = Jsoup.connect(baiBao.getLink()).get();
                    Elements elements = document.getElementsByClass("sidebar-1");

                    Elements breadcrumb = document.getElementsByClass("breadcrumb");
                    Elements theLoai = breadcrumb.get(0).getElementsByTag("li");
                    Elements tt = theLoai.get(0).getElementsByTag("a");
                    for(Element elements2 : tt){
                        TheLoai = elements2.text();
                        Log.d("aaa","the loai"+TheLoai);

                    }
                    Elements elements1 = document.getElementsByClass("header-content width_common");
                    for(Element elements2 : elements1){
                        Element date = elements2.getElementsByClass("date").first();
                        Date = date.text();
                        Log.d("aaa","Date"+Date);
                    }
                    Elements title = document.getElementsByClass("title-detail");
                    for(Element elements2 : title){
                        Title = title.text();
                        Log.d("aaa","Title"+Title);
                    }
                    Elements mieu_ta = document.getElementsByClass("description");
                    for(Element elements2 : mieu_ta){
                        Description = elements2.text();
                        Log.d("aaa","Miêu tả"+Description);

                    }
                    Elements nd = document.getElementsByClass("fck_detail");
                    Elements element = null;
                    for(int i=0;i<nd.size();i++){
                        element = nd.get(i).getElementsByTag("p");
                    }
                    for(Element ct : element){
                        String ct1 = ct.text();
                        Log.d("aaa","Noi dung"+ct1);
                        listNd.add(ct1);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                String finalTitle = Title;
                String finalDate = Date;
                String finalDescription = Description;
                String finalTheLoai = TheLoai;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.tvTheLoai.setText(finalTheLoai);
                        binding.tvDate.setText(finalDate);
                        binding.tvTitledetail.setText(finalTitle);
                        binding.tvDescription.setText(finalDescription);
                        for(String nd : listNd){
                            binding.tvNoiDung.append(nd+"\n\n");
                        }
                    }
                });
            }
        }).start();
    }

    private void moDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_co_chu);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams wLayoutParams = window.getAttributes();
        wLayoutParams.gravity = gravity;
        window.setAttributes(wLayoutParams);

        // clicks owr ngafo
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(false);
        }else{
            dialog.setCancelable(false);
        }
        // khai báo phải có dialog
        TextView tv = dialog.findViewById(R.id.tvClose);
        SeekBar seekBar = dialog.findViewById(R.id.seekBar);
        Button btSFSanSerif = dialog.findViewById(R.id.btSFScanSerif);
        Button btBooke = dialog.findViewById(R.id.btBookerly);
        RadioGroup rdoGroup = dialog.findViewById(R.id.rdoGroup);
        RadioButton rdb1 = dialog.findViewById(R.id.rdt1);
        RadioButton rdb2 = dialog.findViewById(R.id.rdt2);
        RadioButton rdb3 = dialog.findViewById(R.id.rdt3);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // hàm thay đổi kích thức của chữ
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            // thay đổi liên tục
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            // bắt đầu chạm
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            // stop
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int kt = 8; // kích thức mặc định của chữ
                int ktTitle = 24;
                int ktNoiDung = 17;
                binding.tvTheLoai.setTextSize(kt+seekBar.getProgress());
                binding.tvDate.setTextSize(kt+seekBar.getProgress());
                binding.tvTitledetail.setTextSize(ktTitle+seekBar.getProgress());
                binding.tvDescription.setTextSize(ktNoiDung+seekBar.getProgress());
                binding.tvNoiDung.setTextSize(ktNoiDung+seekBar.getProgress());
            }
        });
        // thay đổi font chữ
        btSFSanSerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface font = Typeface.createFromAsset(getAssets(),"Serifa Thin Italic.ttf");
                //binding.tvKichCo.setTypeface(font);
                binding.tvTheLoai.setTypeface(font);
                binding.tvDate.setTypeface(font);
                binding.tvTitledetail.setTypeface(font);
                binding.tvDescription.setTypeface(font);
                binding.tvNoiDung.setTypeface(font);
            }
        });
        btBooke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface font1 = Typeface.createFromAsset(getAssets(),"Bookerly.ttf");
                binding.tvTheLoai.setTypeface(font1);
                binding.tvDate.setTypeface(font1);
                binding.tvTitledetail.setTypeface(font1);
                binding.tvDescription.setTypeface(font1);
                binding.tvNoiDung.setTypeface(font1);
            }
        });

        // thay đổi màu chữ và màu nền
        rdoGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rdt1:
                        binding.tvDate.setTextColor(mauDen);
                        binding.tvTitledetail.setTextColor(mauDen);
                        binding.tvDescription.setTextColor(mauDen);
                        binding.tvNoiDung.setTextColor(mauDen);
                        binding.layout.setBackgroundColor(mauTrang);
                        break;
                    case R.id.rdt2:
                        binding.tvDate.setTextColor(mauTrang);
                        binding.tvTitledetail.setTextColor(mauTrang);
                        binding.tvDescription.setTextColor(mauTrang);
                        binding.tvNoiDung.setTextColor(mauTrang);
                        binding.layout.setBackgroundColor(mauDen);
                        binding.layout2.setBackgroundColor(mauCam);
                        break;
                }
            }
        });

        dialog.show();
    }


}