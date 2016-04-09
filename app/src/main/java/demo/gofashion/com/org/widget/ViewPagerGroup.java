package demo.gofashion.com.org.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.adapter.ImagePagerAdapter;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;

/**
 * Created by Administrator on 2016/3/25.
 */
public class ViewPagerGroup extends RelativeLayout {
    private static final String TAG =ViewPagerGroup.class.getSimpleName() ;
    private ViewPager viewpager;
    private TextView text_image_num;

    private List<View> listImage=new ArrayList<>();
    private ImagePagerAdapter imagePagerAdapter;

    public ViewPagerGroup(Context context) {
        super(context);
        initData();
    }

    public ViewPagerGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public ViewPagerGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_group_layout, this, true);
        //找到控件
        viewpager = ((ViewPager) view.findViewById(R.id.viewpager_detail));

        text_image_num = ((TextView) view.findViewById(R.id.text_image_num));

        text_image_num.setBackgroundResource(R.drawable.bg_shape_circle);
    }

    public void setImageUrl(List<String> urls){
        for (int i = 0; i < urls.size(); i++) {
            if(!TextUtils.isEmpty(urls.get(i))){
                ImageView img=new ImageView(getContext());
                // 设置比例
                img.setScaleType(ImageView.ScaleType.FIT_XY);

                // 设置参数
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

                img.setLayoutParams(params);
                //从图片加载器中获得图片
                DisplayImageOptions options = ImageLoaderUtils.getDisplayImageOptions();
                ImageLoader.getInstance().displayImage(urls.get(i), img, options);
                listImage.add(img);
            }else{
                Log.e(TAG, "setImageUrl: 路径不对");
            }
        }
        //设置为默认第一张数据
       text_image_num.setText("1/"+urls.size());
        if(listImage.size()>0&&listImage.size()==urls.size()){
            Log.e(TAG, "setImageUrl: listImage 进来了");
            imagePagerAdapter = new ImagePagerAdapter(listImage);
            //设置给Viewpager
            viewpager.setAdapter(imagePagerAdapter);
        }

        //为Viwpager添加监听事件
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              text_image_num.setText(((position % listImage.size()+1)) +"/"+listImage.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
