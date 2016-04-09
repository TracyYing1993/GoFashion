package demo.gofashion.com.org.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import demo.gofashion.com.org.adapter.CommendAdapter;
import demo.gofashion.com.org.bean.CommendBean;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by Administrator on 2016/3/26.
 * 评论Activity
 */
public class CommendActivity extends AppCompatActivity implements OkHttpUtils.callBack {
    private double commendNum;
    private int commendCount;
    private String url;
    private TextView text_rating_commend;
    private RatingBar ratingBar_commend;
    private TextView text_commend;
    private ListView listView_commend;
    private TextView text_empty_commend;
    private SwipeRefreshLayout swipeFreshLayout;

    private boolean isRefresh, isBottom;

    private int page = 0;
    private View footView;

    private CommendAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commend_activity);
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");
        commendNum = bundle.getDouble("commendNum");
        commendCount = bundle.getInt("commendCount");

        //综合评价
        text_rating_commend = ((TextView) findViewById(R.id.text_rating_commend));
        text_rating_commend.setText("综合评价: " + commendNum);

        //RatingBar
        ratingBar_commend = ((RatingBar) findViewById(R.id.ratingBar_commend));
        ratingBar_commend.setRating((float) commendNum);

        //空数据时就显示
        text_empty_commend = ((TextView) findViewById(R.id.text_empty_commend));

        //刷新控件
        swipeFreshLayout = ((SwipeRefreshLayout) findViewById(R.id.swipeFreshLayout));

        //评论
        text_commend = ((TextView) findViewById(R.id.text_commend));
        text_commend.setText("评价(" + commendCount + ")");


        if (!TextUtils.isEmpty(url)) {

            adapter = new CommendAdapter(this);
            //连网加载数据
            OkHttpUtils.callBackUIDataFormatOne(OkHttpUtils.getReplaceFormat(url, "%p", page), OkHttpUtils.TYPE_TEXT, this);
        }

        //Listview
        listView_commend = ((ListView) findViewById(R.id.listView_commend));

        footView = LayoutInflater.from(this).inflate(R.layout.footer_view_layout, listView_commend, false);
        footView.setVisibility(View.GONE);

        listView_commend.setEmptyView(text_empty_commend);

        listView_commend.addFooterView(footView);

        listView_commend.setAdapter(adapter);

        swipeFreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        //设置刷新监听事件
        swipeFreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updataCommdent();
            }
        });
        listView_commend.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && isBottom) {
                    footView.setVisibility(View.VISIBLE);
                    OkHttpUtils.callBackUIDataFormatOne(OkHttpUtils.getReplaceFormat(url, "%p", ++page), OkHttpUtils.TYPE_TEXT, CommendActivity.this);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isBottom = ((firstVisibleItem + visibleItemCount) == totalItemCount);
            }
        });


    }

    private void updataCommdent() {
        isRefresh = true;
        //连网加载数据
        OkHttpUtils.callBackUIDataFormatOne(OkHttpUtils.getReplaceFormat(url, "%p", 0), OkHttpUtils.TYPE_TEXT, this);
    }

    @Override
    public void callBackUIString(String data) {
        if (!TextUtils.isEmpty(data)) {
            //隐藏footView
            footView.setVisibility(View.GONE);
            if (isRefresh) {
                isRefresh = false;
                swipeFreshLayout.setRefreshing(false);
                Toast.makeText(CommendActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
                adapter.getRefreshData();
            }
            CommendBean commendBean = new Gson().fromJson(data, CommendBean.class);
            adapter.addData(commendBean.getData());

        }
    }

    @Override
    public void callBackUIByte(byte[] datas) {

    }

    public void clickButton(View view) {
        finish();
    }
}
