package com.example.android45_app_doc_bao_user.dsdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.SQLHelper;
import com.example.android45_app_doc_bao.databinding.ActivityDaDocGanDayBinding;
import com.example.android45_app_doc_bao_doc_chi_tiet.Activiti2_Doc_Chi_Tiet;
import com.example.android45_app_doc_bao_trang_chu.BaiBao;
import com.example.android45_app_doc_bao_user.User;
import com.example.android45_app_doc_bao_user.dsluy.AdapterDaLuy;
import com.example.android45_app_doc_bao_user.dsluy.IConClickDaLuy;

public class ActivityDaDocGanDay extends AppCompatActivity {

    ActivityDaDocGanDayBinding binding;
    SQLHelper sqlHelper = new SQLHelper(this);
    AdapterDaLuy adapterDaLuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_da_doc_gan_day);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(),
                1,RecyclerView.VERTICAL,false);
        adapterDaLuy = new AdapterDaLuy(sqlHelper.getAllBaiBaoDaDoc1());
        binding.rcvBaiBaoDaLuy.setLayoutManager(layoutManager);
        binding.rcvBaiBaoDaLuy.setAdapter(adapterDaLuy);

        adapterDaLuy.setiConClickDaLuy(new IConClickDaLuy() {
            @Override
            public void ClickLink(BaiBao baiBao) {
                Intent intent = new Intent(getBaseContext(), Activiti2_Doc_Chi_Tiet.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("baibaoC",baiBao);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            @Override
            public void ClickId(int id) {
                Toast.makeText(getBaseContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                sqlHelper.onDeleteBaiDaDoc1(id);
                binding.rcvBaiBaoDaLuy.setAdapter(adapterDaLuy);
            }
        });

        binding.imgMuiTenDaDanhDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), User.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_input,R.anim.anim_out);
            }
        });
    }
}