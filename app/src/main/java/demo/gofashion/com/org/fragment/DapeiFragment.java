package demo.gofashion.com.org.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.adapter.DapeiFragmentPagerAdapter;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by Administrator on 2016/3/24.
 */
public class DapeiFragment extends Fragment {

    private View view;
    private ViewPager viewpager_dapei;
    private RadioGroup radio_group_dapei;
    private DapeiFragmentPagerAdapter dapeiFragmentPagerAdapter;
    private int childCount;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] paths = {OkHttpUtils.getReplaceFormat(UrlUtils.MAN_AND_WOMAN_URL, "%u", 2), OkHttpUtils.getReplaceFormat(UrlUtils.MAN_AND_WOMAN_URL, "%u", 1)};

        dapeiFragmentPagerAdapter = new DapeiFragmentPagerAdapter(getChildFragmentManager(), paths);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.dapei_fragment_layout, container, false);
        }
        findViews();

        return view;
    }

    private void findViews() {

        viewpager_dapei = ((ViewPager) view.findViewById(R.id.viewpager_dapei));
        radio_group_dapei = ((RadioGroup) view.findViewById(R.id.radio_group_dapei));
        childCount = radio_group_dapei.getChildCount();
        viewpager_dapei.setAdapter(dapeiFragmentPagerAdapter);

        ((RadioButton) radio_group_dapei.getChildAt(0)).setChecked(true);
        radio_group_dapei.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int childCount = group.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    RadioButton radio= (RadioButton) group.getChildAt(i);
                   if(radio.getId()==checkedId) {
                       viewpager_dapei.setCurrentItem(i);
                   }
                }
            }
        });




        //为ViewPager添加监听事件
        viewpager_dapei.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    ((RadioButton) radio_group_dapei.getChildAt(0)).setChecked(true);
                }else if(position==1){
                    ((RadioButton) radio_group_dapei.getChildAt(2)).setChecked(true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
