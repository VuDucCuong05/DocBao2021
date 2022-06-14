package com.example.android45_app_doc_bao_trang_chu;

import android.content.Intent;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android45_app_doc_bao.MainActivity;
import com.example.android45_app_doc_bao.R;
import com.example.android45_app_doc_bao.databinding.TrangChuBinding;
import com.example.android45_app_doc_bao.main.Fragment_trang_chu;
import com.example.android45_app_doc_bao_doc_chi_tiet.Activiti2_Doc_Chi_Tiet;
import com.example.android45_app_doc_bao_rcv_menu.AdapterRcvMenu;
import com.example.android45_app_doc_bao_rcv_menu.ThuMuc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrangChu extends Fragment {

    TrangChuBinding binding;
    AdapterBaiBao adapter;
    ArrayList<BaiBao> listBaiBao;
    ArrayList<BaiBao> listDocGanDay;
    String link ;
    public TrangChu(String link) {
        this.link = link;
    }
    public TrangChu newInstance() {

        Bundle args = new Bundle();

        TrangChu fragment = new TrangChu(link);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.trang_chu,container,false);

        String url = "https://vnexpress.net/rss/tin-moi-nhat.rss";
        new ReadRSS().execute(link);

        listBaiBao = new ArrayList<>();
        listDocGanDay =  new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getBaseContext(),
                1,RecyclerView.VERTICAL,false);
        adapter = new AdapterBaiBao(listBaiBao);
        binding.rcvBaiBao.setLayoutManager(layoutManager);
        binding.rcvBaiBao.setAdapter(adapter);

        adapter.setiConClickRCVBaiBao(new IConClickRCVBaiBao() {
            @Override
            public void ClickLink(BaiBao baiBao) {
                listDocGanDay.add(baiBao);
                Intent intent = new Intent(getActivity(), Activiti2_Doc_Chi_Tiet.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("baibaoC",baiBao);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
    /// đọc file rss
    private String docNoiDungTuURl(String theUrl){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(theUrl);
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
    private class ReadRSS extends AsyncTask<String,Void,String>{

        // hàm sử lý
        @Override
        protected String doInBackground(String... strings) {
            return docNoiDungTuURl(strings[0]);
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
                String linkAnh="https://th.bing.com/th/id/OIP.WTs9LP-_C9oVd2HH2xX1lAHaE8?pid=ImgDet&rs=1";
                listBaiBao.add(new BaiBao(i,tieuDe,link,linkAnh,introduce,"false"));
            }
            adapter.notifyDataSetChanged();

        }
    }
}
