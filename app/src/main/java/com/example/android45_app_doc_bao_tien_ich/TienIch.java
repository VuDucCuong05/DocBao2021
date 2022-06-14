package com.example.android45_app_doc_bao_tien_ich;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android45_app_doc_bao.MainActivity;
import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.databinding.ActivityTienIchBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TienIch extends AppCompatActivity {

    ActivityTienIchBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tien_ich);
        binding.imgMuiTenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TienIch.this, MainActivity.class);
//                startActivity(intent);
                onBackPressed();
            }
        });

        GetCurrentWertherData("HaNoi");
        binding.btThoiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
    private void GetCurrentWertherData(String data){
        // thực thi các yêu cầu gửi đi ( request : yêu cầu)
        RequestQueue requestQueue = Volley.newRequestQueue(TienIch.this);
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&lang=vi&appid=716a9bd9c213ad83a477cd0d1478b72f";
        // kiểu dữ liệu gửi lên đọc đường link, giá trị đọc được, lỗi trả về
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String day = jsonObject.getString("dt");
                            String name = jsonObject.getString("name");
                            binding.tvDiaDiem.setText(name);
                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000);// chuyển về mini giây
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
                            String Day = simpleDateFormat.format(date);
                            //binding.tvNgayCapNhat.setText(Day);
                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObjectWeather.getString("description");
                            String icon = jsonObjectWeather.getString("icon");
                            Picasso.get()
                                    .load("http://openweathermap.org/img/w/"+icon+".png")
                                    .into(binding.imgThoiTiet);
                            binding.tvTrangThat.setText("Trạng thát: "+status);
                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String nhietDo = jsonObjectMain.getString("temp");
                            Double a = Double.valueOf(nhietDo);
                            String NhietDo = String.valueOf(a.intValue());
                            binding.tvNhietDo.setText(NhietDo+"°C");
                            String doAm = jsonObjectMain.getString("humidity");
                            binding.tvDoAm.setText(doAm+ "%");
                            JSONObject jsonObjectClouds = jsonObject.getJSONObject("clouds");
                            String may = jsonObjectClouds.getString("all");
                            binding.tvMay.setText(may+"%");
                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String gio = jsonObjectWind.getString("speed");
                            binding.tvGio.setText(gio+"m/s");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "Có lỗi", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(stringRequest);
    }
}