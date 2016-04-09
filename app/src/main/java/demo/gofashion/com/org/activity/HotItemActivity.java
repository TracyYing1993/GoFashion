package demo.gofashion.com.org.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import demo.gofashion.com.org.bean.HotInsideBean;
import demo.gofashion.com.org.fragment.ClassifyDetailFragment;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

public class HotItemActivity extends AppCompatActivity implements OkHttpUtils.callBack{
    private TabLayout tabLayout;
    private String temp_id;
    private String url;
    private String topNew ;
    private String hotsell;
    private String category;
    private Gson gson;
    private FragmentManager fragmentManager;
    private TextView textView;
    private String title;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_detail);
        temp_id = getIntent().getExtras().getString("temp_id");
        title = getIntent().getExtras().getString("title");
        initView();
        Log.e("TAG", "temp" + temp_id);
        url = UrlUtils.HotDetail.replace("%d",temp_id);

        OkHttpUtils.callBackUIDataFormatOne(url,OkHttpUtils.TYPE_TEXT,this);
        Log.e("TAG","xxxxxxxxx"+category);
        gson = new Gson();
        fragmentManager = getSupportFragmentManager();
        tabLayout = ((TabLayout) findViewById(R.id.detail_tablayout));
        //initTablayout();
    }

    private void initView() {
        textView = ((TextView) findViewById(R.id.fl_search));
        textView.setText(title);
        imageView = ((ImageView) findViewById(R.id.class_detail_back));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initTablayout() {
        topNew = UrlUtils.TopNew2.replace("%d",category);
        hotsell = UrlUtils.Hotsell2.replace("%d",category);
        tabLayout.addTab(tabLayout.newTab().setText("上新").setTag(topNew));
        tabLayout.addTab(tabLayout.newTab().setText("上新").setTag(hotsell));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                ClassifyDetailFragment detailFragment = new ClassifyDetailFragment();
                url = (String) tab.getTag();

                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                detailFragment.setArguments(bundle);
                transaction.replace(R.id.detail_fragmentContainer, detailFragment);
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void callBackUIString(String data) {
        HotInsideBean hotInsideBean = gson.fromJson(data, HotInsideBean.class);
        HotInsideBean.DataEntity dataEntity = hotInsideBean.getData().get(0);
        HotInsideBean.DataEntity.SubsEntity subsEntity = dataEntity.getSubs().get(0);
        category = subsEntity.getTemp_id();
        initTablayout();
    }

    @Override
    public void callBackUIByte(byte[] datas) {

    }
}
