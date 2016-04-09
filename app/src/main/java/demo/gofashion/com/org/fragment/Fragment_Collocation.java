package demo.gofashion.com.org.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

import demo.gofashion.com.org.activity.Click_More_Activity;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.adapter.CollocationAdapter;
import demo.gofashion.com.org.bean.CollocationBean;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;
import demo.gofashion.com.org.widget.AnimEmptyViewLoading;

/**
 * Created by 杨裕森 on 2016-03-24.
 */
public class Fragment_Collocation extends Fragment {
    //男装	
  GridView gridView;
    Gson gson;
    List<CollocationBean.DataEntity> list;
    DisplayImageOptions options;
    CollocationAdapter adapter ;
    boolean isstart; //判断是否到达底部
    int pager = 0;
    Context context;
    String path ;
    private View footView;
    private LinearLayout viewsContainer;
    private AnimEmptyViewLoading emptyViewLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
        gson = new Gson();
        list = new ArrayList<>();
        options = ImageLoaderUtils.getDisplayImageOptions();
        adapter  =new CollocationAdapter(getActivity(),list);
        path  =getArguments().getString("path");

        path =OkHttpUtils.getReplaceFormat(path,"%p",pager);
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collocation_common_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.gridview_common);
        emptyViewLoading= ((AnimEmptyViewLoading) view.findViewById(R.id.collocation_emptyview));
        gridView.setEmptyView(emptyViewLoading);

        viewsContainer= ((LinearLayout) view.findViewById(R.id.viewsContainer_collocation));
        downloadString(path);
        adapter.notifyDataSetChanged();
        gridView.setAdapter(adapter);
        footView = LayoutInflater.from(getActivity()).inflate(R.layout.footer_view_layout, gridView, false);
        //隐藏页脚控件
        footView.setVisibility(View.GONE);
        viewsContainer.addView(footView);

        loadingNext();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id1 = list.get(position).getId();
               // Fragment_ClickItem_ClickItem clickItem = new Fragment_ClickItem_ClickItem();
                Intent intent = new Intent(getActivity(),Click_More_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",id1);
                intent.putExtras(bundle);
                startActivity(intent);

                Log.e("MainActivity"," 点击到这里了");


            }
        });


        return view;
    }


    public void loadingNext(){
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE&&isstart){
                    footView.setVisibility(View.VISIBLE);
                    downloadString(OkHttpUtils.getReplaceFormat(path,"%p",++pager));
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isstart  =((firstVisibleItem+visibleItemCount)==totalItemCount);
            }
        });

    }

    private void downloadString(String man_url) {
        OkHttpUtils.callBackUIDataFormatOne(man_url, OkHttpUtils.TYPE_TEXT, new OkHttpUtils.callBack() {
            @Override
            public void callBackUIString(String data) {
                footView.setVisibility(View.GONE);
                CollocationBean collocationBean = gson.fromJson(data, CollocationBean.class);
                List<CollocationBean.DataEntity> dataEntity = collocationBean.getData();
                list.addAll(dataEntity);
                //测试数据是否存在
                for(int i=0;i<list.size();i++){
                    String nick_name = list.get(i).getNick_name();
                    Log.e("MainActivity",nick_name);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void callBackUIByte(byte[] datas) {

            }
        });

    }


}
