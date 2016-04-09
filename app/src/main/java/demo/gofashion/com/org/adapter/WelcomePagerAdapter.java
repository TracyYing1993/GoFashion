package demo.gofashion.com.org.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import demo.gofashion.com.org.activity.HomeActivity;
import demo.gofashion.com.org.activity.R;

/**
 * Created by Administrator on 2016/3/24.
 * 欢迎界面中ViewPager所绑定的Adapter
 */
public class WelcomePagerAdapter  extends PagerAdapter {
    private List<View> imgList;
    private Context context;

    public WelcomePagerAdapter(List<View> imgList, Context context) {
        this.imgList = imgList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.imgList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        ((ViewPager) container).addView(this.imgList.get(position));
        if (position == 0){  //如果是欢迎界面的第一张图片
            Button btn = (Button) container.findViewById(R.id.welcome_skip);
            //设置图片按钮监听，跳转到HomeActivity
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();  //销毁WelcomeActivity
                }
            });

        }

        if (position == imgList.size()-1){  //如果是欢迎界面的最后一张图片
            Button btn = (Button) container.findViewById(R.id.welcome_enter);
            //设置图片按钮监听，跳转到HomeActivity
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HomeActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();  //销毁WelcomeActivity
                }
            });

        }
        return this.imgList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(this.imgList.get(position));
    }
}

