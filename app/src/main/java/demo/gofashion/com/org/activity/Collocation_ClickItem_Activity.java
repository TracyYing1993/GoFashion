package demo.gofashion.com.org.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import demo.gofashion.com.org.adapter.CollocationItemAdapter;
import demo.gofashion.com.org.bean.CollocationItemBean;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by 杨裕森 on 2016-03-24.
 */
public class Collocation_ClickItem_Activity extends AppCompatActivity {
    String url ="http://api.funwear.com/mbfun_server/index.php?m=Collocation&a=getCollocationDetailsV2&token=&cid=%c";
    String id;
    ListView listview;
    Gson gson ;
    List<CollocationItemBean> list;
    CollocationItemAdapter adapter ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collocation_clickitem_layout);
        initData();

        adapter = new CollocationItemAdapter(list,this);
        downloadString(url);

        listview.setAdapter(adapter);

    }
    public void initData(){
        Intent intent = getIntent();

        Log.e("MainActivity", "intent传的值"+intent.getStringExtra("id"));

        Bundle extras = intent.getExtras();
        id = extras.getString("id");
        Log.e("MainActivity", "bundle传的值"+id);
        int i = Integer.parseInt(id, 10);
        Log.e("MainActivity",i+"");
        url = OkHttpUtils.getReplaceFormat(url, "%c", i);
        listview = (ListView) this.findViewById(R.id.clickitem_listview);
        gson  =new Gson();
        list = new ArrayList<>();
    }


    private void downloadString(String url) {
        OkHttpUtils.callBackUIDataFormatOne(url, OkHttpUtils.TYPE_TEXT, new OkHttpUtils.callBack() {
            @Override
            public void callBackUIString(String data) {
                CollocationItemBean collocationItemBean = gson.fromJson(data, CollocationItemBean.class);
                list.add(collocationItemBean);
                //测试数据是否下载
                adapter.notifyDataSetChanged();
                String cate_value = list.get(0).getData().getProduct_list().get(0).getCate_value();
                String tab_str = list.get(0).getData().getTab_str();

                Log.e("MainActivity",tab_str);

            }

            @Override
            public void callBackUIByte(byte[] datas) {

            }
        });

    }


}
