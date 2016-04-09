package demo.gofashion.com.org.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import demo.gofashion.com.org.fragment.Fragment_Collocation;

/**
 * Created by Administrator on 2016/3/28.
 */
public class DapeiFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment>list=new ArrayList<>();

    public DapeiFragmentPagerAdapter(FragmentManager fm, String[] paths) {
        super(fm);
       if(paths!=null){
           for (int i = 0; i < paths.length; i++) {
               Fragment_Collocation fragment= new Fragment_Collocation(); //搭配
               Bundle bundle=new Bundle();
               bundle.putString("path", paths[i]);
               fragment.setArguments(bundle);
               list.add(fragment);
           }
       }
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
