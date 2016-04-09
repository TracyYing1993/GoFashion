package demo.gofashion.com.org.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SearchActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tabLayout = ((TabLayout) findViewById(R.id.search_tabLayout));
        imageView = ((ImageView) findViewById(R.id.search_back));
        initTabLayout();
        initimageView();
    }

    private void initimageView() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("单品"));
        tabLayout.addTab(tabLayout.newTab().setText("搭配"));
        tabLayout.addTab(tabLayout.newTab().setText("用户"));
        tabLayout.addTab(tabLayout.newTab().setText("品牌"));
        tabLayout.addTab(tabLayout.newTab().setText("标签"));
    }
}
