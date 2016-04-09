package demo.gofashion.com.org.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.gofashion.com.org.activity.GridViewDetailActivity;
import demo.gofashion.com.org.activity.LoginActivity;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.adapter.IndexGridviewAdapter;
import demo.gofashion.com.org.adapter.IndexListViewAdapter;
import demo.gofashion.com.org.bean.FashionIndexBean;
import demo.gofashion.com.org.bean.IndexGirdviewBean;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;
import demo.gofashion.com.org.widget.AnimEmptyViewLoading;
import demo.gofashion.com.org.widget.SelfScrollGridView;
import demo.gofashion.com.org.widget.SelfScrollListView;

/**
 * Created by Administrator on 2016/3/24.
 * 首页Fragment
 */
public class IndexFragment extends Fragment implements OkHttpUtils.callBack {

    private static final String TAG = IndexFragment.class.getSimpleName();
    private View view;

    @Bind(R.id.gridview_commodity)
    SelfScrollGridView gridview;
    List<IndexGirdviewBean.DataEntity.ProductListEntity> dataEntity = new ArrayList<>();

    int pager = 0;

    IndexGridviewAdapter gridAdapter;


    //自定义的Listview用于解决与Gridview间滚动条的冲突
    @Bind(R.id.listView_Home)
    SelfScrollListView listView;

    //嵌套的ScrollView
    @Bind(R.id.scrollView_index)
    NestedScrollView scrollView;

    //精品容器布局
    @Bind(R.id.products_container)
    LinearLayout products_container;


    //Listvew和GridView的容器布局
    @Bind(R.id.viewsContainer)
    LinearLayout viewsContainer;

    @Bind(R.id.index_emptyview)
    AnimEmptyViewLoading index_emptyview;
    @Bind(R.id.sy_more)
    ImageView sy_more;
    @Bind(R.id.sy_shop)
    ImageView sy_shop;

    private boolean isClick=true;
    private ClassifyFragment.OnDrawerLayoutIsOpenListener listener;


    private IndexListViewAdapter adapter;
    private Gson gson;
    private FashionIndexBean fashionBean;
    private View footView;

    private boolean isRefreshListView, isRefreshGridView;//标记是否刷新

    private boolean isDrag;//是否拖动

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener= (ClassifyFragment.OnDrawerLayoutIsOpenListener) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //实例化Gson
        gson = new Gson();

        //实例化适配器
        adapter = new IndexListViewAdapter(getActivity());
        //连网下载数据
        OkHttpUtils.callBackUIDataFormatOne(UrlUtils.INDEX_PATH, OkHttpUtils.TYPE_TEXT, this);

        //实例化GridView的适配器
        gridAdapter = new IndexGridviewAdapter(getActivity());
        //连网下载GridView的数据
        OkHttpUtils.callBackUIDataFormatOne(OkHttpUtils.getReplaceFormat(UrlUtils.INDEX_PAGER_PATH, "%d", pager), OkHttpUtils.TYPE_TEXT, callback);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.sy_fragment_layout, container, false);
            ButterKnife.bind(this, view);
        }
        //初始化控件
        initViews();
        gridViewData();
        return view;
    }


    int y;

    private void gridViewData() {
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:

                        if (scrollView.getChildAt(0).getMeasuredHeight() <= v.getHeight() + v.getScrollY()) {
                            //下载数据
                                loadGridViewData();
                        }
                        break;
                }
                return false;

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IndexGirdviewBean.DataEntity.ProductListEntity product= (IndexGirdviewBean.DataEntity.ProductListEntity) gridAdapter.getItem(position);
                //取出产品码
                String product_sys_code = product.getProduct_sys_code();
                String url = OkHttpUtils.getReplaceFormat(UrlUtils.INDEX_DETAIL_PATH, "%d", Integer.parseInt(product_sys_code));
                startActivity(new Intent(getActivity(), GridViewDetailActivity.class).putExtra("url",url));
            }
        });
    }

    private void loadGridViewData() {
        footView.setVisibility(View.VISIBLE);

        String replaceFormat = OkHttpUtils.getReplaceFormat(UrlUtils.INDEX_PAGER_PATH, "%d", ++pager);
        Log.e(TAG, "onTouch: url=" + replaceFormat);
        //连网下载分页数据
        OkHttpUtils.callBackUIDataFormatOne(replaceFormat, OkHttpUtils.TYPE_TEXT, callback);
    }


    OkHttpUtils.callBack callback = new OkHttpUtils.callBack() {
        @Override
        public void callBackUIString(String data) {
            //显示精品
            products_container.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(data)) {

                IndexGirdviewBean indexGirdviewBean = gson.fromJson(data, IndexGirdviewBean.class);
                IndexGirdviewBean.DataEntity data1 = indexGirdviewBean.getData();
                List<IndexGirdviewBean.DataEntity.ProductListEntity> productList = data1.getProductList();

                if (isRefreshGridView) {
                    gridAdapter.getRefreshData();//刷新数据
                    isRefreshGridView = false;
                }

                //为适配器添加数据
                gridAdapter.addData(productList);
                for (int i = 0; i < productList.size(); i++) {
                    String product_name = productList.get(i).getProduct_name();
                    Log.e("MainActivity", product_name);
                }

            } else {
                Toast.makeText(getActivity(), "网络加载错误", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void callBackUIByte(byte[] datas) {

        }
    };

    //初始化控件
    private void initViews() {

        //为Listview设置适配器
        listView.setAdapter(adapter);
        listView.setEmptyView(index_emptyview);

        //为GridView设置适配器
        gridview.setAdapter(gridAdapter);

        footView = LayoutInflater.from(getActivity()).inflate(R.layout.footer_view_layout, gridview, false);
        //隐藏页脚控件
        footView.setVisibility(View.GONE);
        viewsContainer.addView(footView);

        sy_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick) {
                    listener.isOpenDrawerLayout(1);
                    //打开
                    isClick = !isClick;
                } else {
                    listener.isOpenDrawerLayout(2);
                    //关闭
                    isClick = !isClick;
                }
            }
        });
        sy_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

    }
/*

    private void updataAllData() {
        isRefreshListView = true;
        isRefreshGridView = true;
        //连网下载数据
        OkHttpUtils.callBackUIDataFormatOne(UrlUtils.INDEX_PATH, OkHttpUtils.TYPE_TEXT, this);
        //连网下载GridView的数据
        OkHttpUtils.callBackUIDataFormatOne(OkHttpUtils.getReplaceFormat(UrlUtils.INDEX_PAGER_PATH, "%d", 1), OkHttpUtils.TYPE_TEXT, callback);
    }
*/

    @Override
    public void callBackUIString(String data) {
        if (data != null) {

            Log.e(TAG, "callBackUIString: 正在下载");
            fashionBean = gson.fromJson(data, FashionIndexBean.class);
            //为适配器绑定数据
            adapter.addData(fashionBean.getData());
        }

    }

    @Override
    public void callBackUIByte(byte[] datas) {

    }
}
