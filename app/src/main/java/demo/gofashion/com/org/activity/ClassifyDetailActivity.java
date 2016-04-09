package demo.gofashion.com.org.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import demo.gofashion.com.org.bean.BrandItemBean;
import demo.gofashion.com.org.fragment.ClassifyDetailFragment;
import demo.gofashion.com.org.utils.UrlUtils;

public class ClassifyDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private BrandItemBean brandItemBean;
    private String topNewUrl;
    private String hotSell;
    private String url;
    private FragmentManager fragmentManager;
    private TextView viewById;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_detail);
        tabLayout = ((TabLayout) findViewById(R.id.detail_tablayout));
        brandItemBean = (BrandItemBean) getIntent().getExtras().getSerializable("brandItem");
        viewById = ((TextView) findViewById(R.id.fl_search));
        imageView = ((ImageView) findViewById(R.id.class_detail_back));
        initImageView();

        String brand_code = brandItemBean.getBrand_code();
        viewById.setText(brand_code);
        topNewUrl = UrlUtils.TopNew.replace("%d", brand_code);
        hotSell = UrlUtils.Hotsell.replace("%d",brand_code);
        fragmentManager = getSupportFragmentManager();


        initTablayout();
    }

    private void initImageView() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initTablayout() {
        tabLayout.addTab(tabLayout.newTab().setText("上新").setTag(topNewUrl));
        tabLayout.addTab(tabLayout.newTab().setText("热销").setTag(hotSell));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                ClassifyDetailFragment detailFragment = new ClassifyDetailFragment();
                url = (String) tab.getTag();

                Bundle bundle = new Bundle();
                bundle.putString("url",url);
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


}
