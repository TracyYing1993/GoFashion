package demo.gofashion.com.org.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import demo.gofashion.com.org.bean.SizeBean;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by Administrator on 2016/3/26.
 */
public class SizeActivity extends AppCompatActivity implements OkHttpUtils.callBack {
    private static final String TAG =SizeActivity.class.getSimpleName() ;
    private String proD_CLS_ID;
    private Gson gson;
    private TableLayout tableLayout;
    private SizeBean sizeBean;
    private TextView empty_text_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.size_table_layout);
        tableLayout = ((TableLayout) findViewById(R.id.tableLayout_size));
        empty_text_size = ((TextView) findViewById(R.id.empty_text_size));

        proD_CLS_ID = getIntent().getStringExtra("codeId");
        Log.e(TAG, "onCreate: proD_CLS_ID"+proD_CLS_ID);
        gson = new Gson();
        if(!TextUtils.isEmpty(proD_CLS_ID)){
            //连网下载数据
            OkHttpUtils.callBackUIDataFormatOne(OkHttpUtils.getReplaceFormat(UrlUtils.INDEX_SIZE_PATH,"%d",proD_CLS_ID),OkHttpUtils.TYPE_TEXT,this);

            Log.e(TAG, "onCreate: 870325="+OkHttpUtils.getReplaceFormat(UrlUtils.INDEX_SIZE_PATH,"%d",proD_CLS_ID));
        }
    }

    @Override
    public void callBackUIString(String data) {
        if(data!=null){
            sizeBean = gson.fromJson(data, SizeBean.class);

            List<SizeBean.ResultsEntity> results = sizeBean.getResults();
            if(results!=null){
                for (int i = 0; i < results.size(); i++) {
                    List<List<String>> size_table = results.get(i).getSize_table();
                    if(size_table!=null) {
                        Log.e(TAG, "callBackUIString: size_table="+ size_table.size());
                        empty_text_size.setVisibility(View.GONE);
                        for (int j = 0; j < size_table.size(); j++) {
                            List<String> strings = size_table.get(i);
                            TableRow row = new TableRow(this);
                            row.setBackgroundColor(Color.parseColor("#9a9a9b"));
                            for (int k = 0; k < strings.size(); k++) {
                                TextView tv = new TextView(this);
                                tv.setText(size_table.get(j).get(k));
                                tv.setTextSize(16);
                                tv.setTextColor(Color.WHITE);
                                if (k == 0) {
                                    tv.setBackgroundColor(Color.parseColor("#eccecece"));
                                }

                                row.addView(tv);

                                Log.e(TAG, "callBackUIString: " + size_table.get(j).get(k));
                            }
                            tableLayout.addView(row);
                        }
                    }else{
                        empty_text_size.setText("该商品暂无尺码参数");
                        empty_text_size.setVisibility(View.VISIBLE);
                    }
                }
                Log.e(TAG, "callBackUIString: tableChildCount="+tableLayout.getChildCount());
            }
        }
    }

    @Override
    public void callBackUIByte(byte[] datas) {

    }

    public void clickButton(View view) {
        this.finish();
    }
}
