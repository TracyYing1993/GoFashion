package demo.gofashion.com.org.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import demo.gofashion.com.org.activity.LoginActivity;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.activity.SearchActivity;
import demo.gofashion.com.org.utils.UrlUtils;

/**
 * Created by Administrator on 2016/3/24.
 *
 * 分类Fragment
 */
public class ClassifyFragment extends Fragment {


    private TabLayout tabLayout;
    private TextView searchView;
    private FragmentManager childFragmentManager;
    private FLInsideFragment flInsideFragment;
    private ImageView fl_more,fl_shop;
    private OnDrawerLayoutIsOpenListener listener;
    private boolean isClick=true;

    public interface OnDrawerLayoutIsOpenListener{
        void isOpenDrawerLayout(int type);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener= (OnDrawerLayoutIsOpenListener) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        childFragmentManager = getChildFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fl_fragment_layout, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.fl_tabLayout);
        searchView = ((TextView) view.findViewById(R.id.fl_search));
        fl_more = ((ImageView) view.findViewById(R.id.fl_more));
        fl_shop = ((ImageView) view.findViewById(R.id.fl_shop));
        initTabLayout();
        initSearchView();
        fl_more.setOnClickListener(new View.OnClickListener() {
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
        fl_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }

    private void initSearchView() {
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("女装").setTag(UrlUtils.women_dress));
        tabLayout.addTab(tabLayout.newTab().setText("男装").setTag(UrlUtils.men_clothing));
        tabLayout.addTab(tabLayout.newTab().setText("鞋履").setTag(UrlUtils.shoes));
        tabLayout.addTab(tabLayout.newTab().setText("箱包").setTag(UrlUtils.bag));
        tabLayout.addTab(tabLayout.newTab().setText("配饰").setTag(UrlUtils.accessories));
        tabLayout.addTab(tabLayout.newTab().setText("美妆").setTag(UrlUtils.Beauty));
        tabLayout.addTab(tabLayout.newTab().setText("生活").setTag(UrlUtils.live));
        FragmentTransaction transaction = childFragmentManager.beginTransaction();
        flInsideFragment = new FLInsideFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", UrlUtils.women_dress);
        flInsideFragment.setArguments(bundle);
        transaction.replace(R.id.fl_fragmentContainer, flInsideFragment);
        transaction.commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FragmentTransaction transaction = childFragmentManager.beginTransaction();
                if(flInsideFragment!=null){
                    transaction.hide(flInsideFragment);
                }
                if(flInsideFragment==null){
                    String url = (String) tab.getTag();
                    Log.e("TAG", "进来了" + url);
                    flInsideFragment = new FLInsideFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("url",url);
                    flInsideFragment.setArguments(bundle);
                    transaction.replace(R.id.fl_fragmentContainer, flInsideFragment);
                }else{
                    flInsideFragment=null;
                    String url = (String) tab.getTag();
                    Log.e("TAG", "进来了" + url);
                    flInsideFragment = new FLInsideFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("url",url);
                    flInsideFragment.setArguments(bundle);
                    transaction.replace(R.id.fl_fragmentContainer, flInsideFragment);
                }
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
