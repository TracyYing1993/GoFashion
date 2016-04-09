package demo.gofashion.com.org.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import demo.gofashion.com.org.adapter.ClickMoreAdapter;
import demo.gofashion.com.org.bean.ClickMoreBean;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by 杨裕森 on 2016-03-24.
 */
public class Click_More_Activity extends AppCompatActivity {
    String url ="http://api.funwear.com/mbfun_server/index.php?m=Topic&a=getTopicList&page=0";
    ListView listview;
    Gson gson ;
    List<ClickMoreBean.DataEntity> dataEntity;
    ClickMoreAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_more_layout);
        initData();

        adapter =new ClickMoreAdapter(dataEntity,this);
        downloadString(url);

        listview.setAdapter(adapter);



    }
    public void initData(){
        listview = (ListView) this.findViewById(R.id.more_listview);
        gson  =new Gson();
        dataEntity= new ArrayList<>();

    }


    private void downloadString(String url) {
        OkHttpUtils.callBackUIDataFormatOne(url, OkHttpUtils.TYPE_TEXT, new OkHttpUtils.callBack() {
            @Override
            public void callBackUIString(String data) {
                ClickMoreBean clickMoreBean = gson.fromJson(data, ClickMoreBean.class);
                List<ClickMoreBean.DataEntity> data1 = clickMoreBean.getData();
                dataEntity.addAll(data1);
                //测试数据是否下载
                for(int i=0;i<dataEntity.size();i++){
                    String info = dataEntity.get(i).getInfo();
                    Log.e("MainActivity",info);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void callBackUIByte(byte[] datas) {
            }
        });

    }


}
