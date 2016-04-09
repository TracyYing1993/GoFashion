package demo.gofashion.com.org.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.adapter.BrandItemAdapter;
import demo.gofashion.com.org.adapter.HotItemAdapter;
import demo.gofashion.com.org.bean.BrandItemBean;
import demo.gofashion.com.org.bean.HotItemBean;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;
import demo.gofashion.com.org.widget.AnimEmptyViewLoading;

/**
 * Created by Administrator on 2016/3/24.
 */
public class FLInsideFragment extends Fragment implements OkHttpUtils.callBack {

    private String url;
    private GridView brandGridView;
    private GridView hotGridView;
    private List<BrandItemBean> brandList = new ArrayList<>();
    private List<HotItemBean> hotList = new ArrayList<>();
    private BrandItemAdapter brandItemAdapter;
    private HotItemAdapter hotAdapter;
    private AnimEmptyViewLoading classify_emptyview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");

        OkHttpUtils.callBackUIDataFormatOne(url, OkHttpUtils.TYPE_TEXT, this);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fl_inside_fragment_layout, container, false);
        classify_emptyview = ((AnimEmptyViewLoading) view.findViewById(R.id.classify_emptyview));
        brandGridView = ((GridView) view.findViewById(R.id.brandGridView));
        brandItemAdapter = new BrandItemAdapter(brandList);
        brandGridView.setEmptyView(classify_emptyview);
        brandGridView.setAdapter(brandItemAdapter);
        hotGridView = ((GridView) view.findViewById(R.id.hotGridView));
        hotAdapter = new HotItemAdapter(hotList);
        hotGridView.setAdapter(hotAdapter);
        return view;
    }

    @Override
    public void callBackUIString(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                if (i == 1) {
                    JSONObject jsonObject1 = data.getJSONObject(i);
                    JSONArray config = jsonObject1.getJSONArray("config");
                    List<BrandItemBean> list = new ArrayList<>();
                    for (int j = 0; j < config.length(); j++) {
                        JSONObject obj = config.getJSONObject(j);
                        BrandItemBean brandItemBean = new BrandItemBean();
                        brandItemBean.setId(obj.getString("id"));
                        brandItemBean.setBrand_code(obj.getString("brand_code"));
                        brandItemBean.setTemp_ip(obj.getString("temp_id"));
                        brandItemBean.setIndex(obj.getInt("id"));
                        brandItemBean.setEname(obj.getString("ename"));
                        brandItemBean.setImgurl(obj.getString("logo_img"));
                        Log.e("TAG", brandItemBean.toString());
                        list.add(brandItemBean);
                    }
                    brandList.addAll(list);
                    brandItemAdapter.notifyDataSetChanged();
                }

                if (i == 3) {
                    JSONObject jsonObject1 = data.getJSONObject(i);
                    JSONArray config = jsonObject1.getJSONArray("config");
                    List<HotItemBean> list = new ArrayList<>();
                    for (int j = 0; j < config.length(); j++) {
                        JSONObject obj = config.getJSONObject(j);
                        HotItemBean hotItemBean = new HotItemBean();
                        hotItemBean.setImgUrl(obj.getString("url"));
                        hotItemBean.setId(obj.getString("id"));
                        hotItemBean.setIndex(obj.getInt("index"));
                        hotItemBean.setInfo(obj.getString("info"));
                        hotItemBean.setName(obj.getString("name"));
                        hotItemBean.setTemp_id(obj.getString("temp_id"));
                        hotItemBean.setType(obj.getString("type"));
                        Log.e("TAG", hotItemBean.toString());
                        list.add(hotItemBean);
                    }

                    hotList.addAll(list);
                    hotAdapter.notifyDataSetChanged();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void callBackUIByte(byte[] datas) {

    }


}
