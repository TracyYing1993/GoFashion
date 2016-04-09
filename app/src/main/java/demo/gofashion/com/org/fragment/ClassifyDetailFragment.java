package demo.gofashion.com.org.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.adapter.DetailRecylerViewAdapter;
import demo.gofashion.com.org.bean.ClassifyBean;
import demo.gofashion.com.org.bean.ClassifyItemBean;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by Administrator on 2016/3/25.
 */
public class ClassifyDetailFragment extends Fragment implements OkHttpUtils.callBack{

    private RecyclerView recyclerView;
    private DetailRecylerViewAdapter adapter;
    private String url;
    private Gson gson;
    private List<ClassifyItemBean> list = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");
        gson = new Gson();
        OkHttpUtils.callBackUIDataFormatOne(url,OkHttpUtils.TYPE_TEXT,this);
        adapter = new DetailRecylerViewAdapter(list);
        Log.e("TAG", "进来了");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classify_detail_fragment_layout, container, false);
        recyclerView = ((RecyclerView) view.findViewById(R.id.detail_recyclerView));
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void callBackUIString(String data) {
        ClassifyBean classifyBean = gson.fromJson(data, ClassifyBean.class);
        List<ClassifyBean.ResultsEntity> results = classifyBean.getResults();
        List<ClassifyItemBean> item_list = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            ClassifyBean.ResultsEntity resultsEntity = results.get(i);
            ClassifyBean.ResultsEntity.ClsInfoEntity clsInfo = resultsEntity.getClsInfo();
            ClassifyItemBean classifyItemBean = new ClassifyItemBean();
            classifyItemBean.setName(clsInfo.getName());
            classifyItemBean.setUrlImg(clsInfo.getMainImage());
            classifyItemBean.setSale_price(clsInfo.getSale_price());
            Log.e("TAG",classifyItemBean.toString());
            item_list.add(classifyItemBean);
        }

        list.addAll(item_list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void callBackUIByte(byte[] datas) {

    }
}
