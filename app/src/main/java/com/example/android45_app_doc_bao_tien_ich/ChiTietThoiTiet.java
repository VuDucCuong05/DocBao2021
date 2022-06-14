package com.example.android45_app_doc_bao_tien_ich;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.databinding.ActivityChiTietThoiTietBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChiTietThoiTiet extends AppCompatActivity {

    ActivityChiTietThoiTietBinding binding;
    ArrayList<ThoiTiet> list;
    AdapterLv adapterLv;
    String dd = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chi_tiet_thoi_tiet);
        list = new ArrayList<>();
        Get7DaysData("hanoi");
        GetCurrentWertherData("hanoi");
        adapterLv = new AdapterLv(ChiTietThoiTiet.this,list);
        binding.lv.setAdapter(adapterLv);

        binding.tvDiaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DialogDiaDiem();
               dd = binding.tvDiaDiem.getText().toString();
                if(dd.equals("")){
                    Get7DaysData("hanoi");
                    GetCurrentWertherData("hanoi");
                    adapterLv = new AdapterLv(ChiTietThoiTiet.this,list);
                    binding.lv.setAdapter(adapterLv);
                }
            }
        });
        Log.d("ttt",""+dd);


        binding.imgMuiTenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    private void Get7DaysData(String data) {
        String url = "http://api.openweathermap.org/data/2.5/forecast?q="+data+"&lang=vi&units=metric&cnt=7&appid=716a9bd9c213ad83a477cd0d1478b72f";
        RequestQueue requestQueue = Volley.newRequestQueue(ChiTietThoiTiet.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObjectCity = jsonObject.getJSONObject("city");
                            String name = jsonObjectCity.getString("name");
                            JSONArray jsonArrayList = jsonObject.getJSONArray("list");
                            for(int i=0; i<jsonArrayList.length(); i++){
                                JSONObject jsonObjectList = jsonArrayList.getJSONObject(i);
                                String ngay = jsonObjectList.getString("dt");
                                long l = Long.valueOf(ngay);
                                Date date = new Date(l*1000);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MM-dd");
                                String Day = simpleDateFormat.format(date);

                                JSONObject jsonObjectTemp = jsonObjectList.getJSONObject("main");
                                String max =  jsonObjectTemp.getString("temp_max");
                                String min =  jsonObjectTemp.getString("temp_min");
                                Double a = Double.valueOf(max);
                                Double b = Double.valueOf(min);
                                String Nhietdomax = String.valueOf(a.intValue())+"°";
                                String Nhietdomin = String.valueOf(b.intValue())+"°";

                                JSONArray jsonArrayWeather = jsonObjectList.getJSONArray("weather");
                                JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                                String status = jsonObjectWeather.getString("description");
                                String catDau = status.substring(0,1);
                                String catCuoi = status.substring(1,status.length());
                                String Status = catDau.toUpperCase()+catCuoi.toLowerCase();

                                String icon = jsonObjectWeather.getString("icon");
                                list.add(new ThoiTiet(Day,Status,icon,Nhietdomax,Nhietdomin));
                            }
                            adapterLv.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "Không có dữ liệu về thành phố của bạn", Toast.LENGTH_SHORT).show();
                    }
                });
        // thực thi gửi yeu cầu
        requestQueue.add(stringRequest);

    }
    private void GetCurrentWertherData(String data){
        // thực thi các yêu cầu gửi đi ( request : yêu cầu)
        RequestQueue requestQueue = Volley.newRequestQueue(ChiTietThoiTiet.this);
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
                            String status = jsonObjectWeather.getString("main");
                            String catDau = status.substring(0,1);
                            String catCuoi = status.substring(1,status.length());
                            String Status = catDau.toUpperCase()+catCuoi.toLowerCase();
                            String icon = jsonObjectWeather.getString("icon");
                            Picasso.get()
                                    .load("http://openweathermap.org/img/w/"+icon+".png")
                                    .into(binding.imgThoiTiet);
                            binding.tvTrangThat.setText(Status);

                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String nhietDo = jsonObjectMain.getString("temp");
                            Double a = Double.valueOf(nhietDo);
                            String NhietDo = String.valueOf(a.intValue());
                            binding.tvNhietDo.setText(NhietDo+"°C");
                            String nhietDoMax = jsonObjectMain.getString("temp_max");
                            Double m = Double.valueOf(nhietDo);
                            String NhietDoMax = String.valueOf(m.intValue());
                            String nhietDoMin = jsonObjectMain.getString("temp_min");
                            Double n = Double.valueOf(nhietDo);
                            String NhietDoMin = String.valueOf(n.intValue());
                            binding.tvNhietDomax.setText(NhietDoMax+"°/"+NhietDoMin+"°");
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
                        Toast.makeText(getBaseContext(), "Không có dữ liệu về thành phố của bạn", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(stringRequest);
    }
    private void DialogDiaDiem(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom_tim_kiem_dd);
        dialog.setCanceledOnTouchOutside(false);

        Button button = dialog.findViewById(R.id.bt_tim_kiem);
        Button btHuy = dialog.findViewById(R.id.bt_huy);
        EditText edtTimKiem = dialog.findViewById(R.id.edtNhapTimK);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTimKiem.getText().toString().equals("") == false){
                    String dd1 = edtTimKiem.getText().toString();
                    Get7DaysData(dd1);
                    GetCurrentWertherData(dd1);
                    adapterLv = new AdapterLv(ChiTietThoiTiet.this,list);
                    binding.lv.setAdapter(adapterLv);
                    dialog.dismiss();
                }else{
                    Toast.makeText(getBaseContext(), "Bạn chưa nhập gì", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}