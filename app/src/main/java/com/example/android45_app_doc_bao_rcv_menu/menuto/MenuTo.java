package com.example.android45_app_doc_bao_rcv_menu.menuto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.android45_app_doc_bao.MainActivity;
import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.SQLHelper;
import com.example.android45_app_doc_bao.databinding.ActivityMenuToBinding;
import com.example.android45_app_doc_bao_doc_chi_tiet.Activiti2_Doc_Chi_Tiet;
import com.example.android45_app_doc_bao_rcv_menu.AdapterRcvMenu;
import com.example.android45_app_doc_bao_rcv_menu.IConClickRcvMenu;
import com.example.android45_app_doc_bao_rcv_menu.ThuMuc;
import com.example.android45_app_doc_bao_trang_chu.AdapterBaiBao;
import com.example.android45_app_doc_bao_trang_chu.BaiBao;
import com.example.android45_app_doc_bao_trang_chu.IConClickRCVBaiBao;
import com.example.android45_app_doc_bao_trang_chu.TrangChu;
import com.example.android45_app_doc_bao_trang_chu.XMLDOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuTo extends AppCompatActivity {
    ActivityMenuToBinding binding;
    AdapterMenuTo adapterMenuTo;
    ArrayList<ThuMuc> list;
    AdapterBaiBao1 adapter;
    SQLHelper sqlHelper;
    List<BaiBao> listDocVe;
    List<String> listThuMuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_menu_to);

        sqlHelper = new SQLHelper(this);
        AppListThuMucTimKiem();
        for(int i=0; i<listThuMuc.size(); i++){
            new ReadRSS().execute(listThuMuc.get(i).toString());
        }
        listDocVe = new ArrayList<>();
        listDocVe = sqlHelper.getAllBaiBaoDaDoc();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext()
                ,1,RecyclerView.VERTICAL,false);
        adapter = new AdapterBaiBao1(listDocVe);
        binding.rcvMenuTo.setLayoutManager(layoutManager);
        binding.rcvMenuTo.setAdapter(adapter);
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        adapter.setiConClickRCVBaiBao(new IConClickRCVBaiBao() {
            @Override
            public void ClickLink(BaiBao baiBao) {
                Intent intent = new Intent(MenuTo.this, Activiti2_Doc_Chi_Tiet.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("baibaoC",baiBao);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.imgMuiTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    private void filter(String text){
        ArrayList<BaiBao> fList = new ArrayList<>();
        for(BaiBao item : listDocVe){
            if(item.getTitel().toLowerCase().contains(text.toLowerCase())){
                fList.add(item);
            }
        }
        adapter.filterList(fList);
    }
    private void AppListThuMucTimKiem(){
        listThuMuc = new ArrayList<>();
        listThuMuc.add("https://vnexpress.net/rss/tin-moi-nhat.rss");
        listThuMuc.add("https://vnexpress.net/rss/thoi-su.rss");
        listThuMuc.add("https://vnexpress.net/rss/the-gioi.rss");
        listThuMuc.add("https://vnexpress.net/rss/kinh-doanh.rss");
        listThuMuc.add("https://vnexpress.net/rss/tin-noi-bat.rss");
        listThuMuc.add("https://vnexpress.net/rss/phap-luat.rss");
        listThuMuc.add("https://vnexpress.net/rss/gia-dinh.rss");
    }
    private void addListThuMuc(){
        list = new ArrayList<>();
        list.add(new ThuMuc(0,"Trang Chủ",R.drawable.trangchu,"https://vnexpress.net/rss/tin-moi-nhat.rss"));
        list.add(new ThuMuc(1,"Thời Sự",R.drawable.thoisu,"https://vnexpress.net/rss/thoi-su.rss"));
        list.add(new ThuMuc(2,"Thế Giới",R.drawable.thegioi,"https://vnexpress.net/rss/the-gioi.rss"));
        list.add(new ThuMuc(3,"Kinh doanh",R.drawable.kinhdoanh,"https://vnexpress.net/rss/kinh-doanh.rss"));
        list.add(new ThuMuc(4,"Startup",R.drawable.thethao,"https://vnexpress.net/rss/tin-noi-bat.rss"));
    }

    private class ReadRSS extends AsyncTask<String,Void,String> {
        // hàm sử lý
        @Override
        protected String doInBackground(String... strings) {
            StringBuffer content = new StringBuffer();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }

                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }
        // hàm trả về
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDescription = document.getElementsByTagName("description");
            String tieuDe = "";
            String anh = "";
            String link = "";
            String introduce = "";
            for(int i=0 ;i<nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                // vị trí, key
                tieuDe = parser.getValue(element,"title");
                link = parser.getValue(element,"link");
                String cdata = nodeListDescription.item(i+1).getTextContent();
                // hàm đọc img
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                if(matcher.find()){
                    anh = matcher.group(1);
                }
                // custom lại ( bài viết đăng cách đâu bây lâu ( giờ hiện tại trừ giờ đăng)
                introduce = parser.getValue(element,"pubDate");
                sqlHelper.addBaibaoDaDoc(new BaiBao(1,tieuDe,link,anh,introduce,"false"));
            }
            //adapter.notifyDataSetChanged();

        }

    }


}