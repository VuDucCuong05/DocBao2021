package com.example.android45_app_doc_bao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.android45_app_doc_bao.databinding.ActivityMainBinding;
import com.example.android45_app_doc_bao.main.Fragment_tien_ich;
import com.example.android45_app_doc_bao.main.Fragment_trang_chu;
import com.example.android45_app_doc_bao.main.Fragment_video;
import com.example.android45_app_doc_bao_rcv_menu.AdapterRcvMenu;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity  {
    // ban ok
    ActivityMainBinding binding;
    AdapterRcvMenu adapterRcvMenu;
    private String link;
    public String getLink() {
        return link;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        getFragment(new Fragment_trang_chu());
        // bottom navigation bar
        binding.btNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.actionTinTuc:
                        getFragment(new Fragment_trang_chu());
                        break;
                    case R.id.actionTienIch:
                        getFragment(new Fragment_tien_ich());
                        break;
                    case R.id.actionVideo:
                        getFragment(new Fragment_video());
                        break;
                }
                return true;
            }
        });
    }
    private void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.flMain,fragment).commit();
    }

    private void XuLyThoat(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Thoát");
        dialog.setIcon(android.R.drawable.ic_dialog_info);
        dialog.setMessage(R.string.dl_thong_bao);
        dialog.setPositiveButton(R.string.dl_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // khởi tại lại main activity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                // sự kiện kết thúc
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });
        dialog.setNegativeButton(R.string.dl_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.create().show();
    }
    @Override
    public void onBackPressed() {
        XuLyThoat();
    }

    // autocomplete text view
/*    private void autocomplete(){
        listsearch = new ArrayList<>();
        listsearch.add("Lập trình Java2");
        listsearch.add("Lập trình Java3");
        listsearch.add("Lập trình Java Kinh tế");
        ArrayAdapter<BaiBao> arrayAdapter = new ArrayAdapter<BaiBao>(this, android.R.layout.simple_dropdown_item_1line,listsearch);
        // số lượng tứ tự bắt đầu tìm kiếm
        binding.edtSearch.setAdapter(arrayAdapter);
        binding.edtSearch.setThreshold(1);
    }*/
}