package demo.gofashion.com.org.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.adapter.RemenListViewAdapter;
import demo.gofashion.com.org.bean.FashionCommunityBean;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ReMenFragment extends Fragment implements OkHttpUtils.callBack{
    private static final String TAG = ReMenFragment.class.getSimpleName();

    private View view;

    @Bind(R.id.remen_listview)
    ListView listview;

    @Bind(R.id.remen_emptyview)
    View emptyview;

    private RemenListViewAdapter adapter;
    private Gson gson;
    private FashionCommunityBean bean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        adapter = new RemenListViewAdapter(getActivity());
        //连网下载数据
        Log.e(TAG," ReMenFragment------------连网下载数据");
        OkHttpUtils.callBackUIDataFormatOne(UrlUtils.HOT, OkHttpUtils.TYPE_TEXT, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.remen_fragment_layout, container, false);
            ButterKnife.bind(this,view);
        }
        listview.setEmptyView(emptyview);
        listview.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void callBackUIString(String data) {
        if (data != null){
            bean = gson.fromJson(data,FashionCommunityBean.class);
            adapter.addData(bean.getData());
        }
    }
    @Override
    public void callBackUIByte(byte[] datas) {

    }
}
