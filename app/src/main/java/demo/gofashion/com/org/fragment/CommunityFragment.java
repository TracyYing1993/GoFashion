package demo.gofashion.com.org.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.adapter.CummunityFragmentPagerAdapter;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by Administrator on 2016/3/24.
 *
 * 社区Fragment
 */
public class CommunityFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewpager;
    private List<Fragment> list = new ArrayList<Fragment>();
    private String[] arrTitles;
    private CummunityFragmentPagerAdapter adapter;

    private boolean isClick=true;
    private ClassifyFragment.OnDrawerLayoutIsOpenListener listener;
    private ImageView sq_camera;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener= (ClassifyFragment.OnDrawerLayoutIsOpenListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sq_fragment_layout, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.sq_tabLayout);
        viewpager = ((ViewPager) view.findViewById(R.id.sq_viewpager));

        ImageView sq_more = (ImageView) view.findViewById(R.id.sq_more);
        sq_camera = ((ImageView) view.findViewById(R.id.sq_camera));

        arrTitles = getResources().getStringArray(R.array.arrTitles);

        ReMenFragment fragment1 = new ReMenFragment(); //热门
        DapeiFragment fragment2= new DapeiFragment(); //搭配
        list.add(fragment1);
        list.add(fragment2);
        adapter = new CummunityFragmentPagerAdapter(getChildFragmentManager(),list,arrTitles);
        viewpager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewpager);

        sq_more.setOnClickListener(new View.OnClickListener() {
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
        sq_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });
        return view;
    }
}
