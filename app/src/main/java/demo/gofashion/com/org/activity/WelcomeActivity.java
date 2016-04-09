package demo.gofashion.com.org.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import demo.gofashion.com.org.adapter.WelcomePagerAdapter;
import demo.gofashion.com.org.utils.db.SharedPfrUtils;

/**
 * Created by Administrator on 2016/3/24.
 */
public class WelcomeActivity extends AppCompatActivity {
    private SharedPfrUtils utils;
    private WelcomePagerAdapter adapter;
    private ViewPager viewPager;
    private RadioGroup radio_container;
    private RadioButton[] arrRadioButton;
    private List<View> imgList;
    private int[] imgArr = {R.mipmap.splash_2,R.mipmap.splash_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // 1.存入到SharePrefrences
        putIntoSharePref(); 
        // 2.初始化数据
        initData();
        // 3.初始化视图
        initView();
        // 4.初始化“点”
        initDot();
    }

    private void putIntoSharePref() {
        utils = new SharedPfrUtils(WelcomeActivity.this,"SharedPreferencesHelper");
        /** 存入到SharePrefrences中 */
        utils.putInt(StartActivity.IS_FIRST_RUN, StartActivity.NOT_FIRST);
    }

    //创建图片资源数据
    private void initData() {
        imgList = new ArrayList<View>();
        //添加第一张图片
        View view1 = LayoutInflater.from(this).inflate(R.layout.welcome_first,null);
        imgList.add(view1);
        //添加中间两张图片
        for (int i = 0; i < imgArr.length; i++) {
            ImageView imageview = new ImageView(this);
            imageview.setBackgroundResource(imgArr[i]);
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgList.add(imageview);
        }
        //添加最后一张图片
        View view2 = LayoutInflater.from(this).inflate(R.layout.welcome_last,null);
        imgList.add(view2);
    }

    private void initView() {
        viewPager = (ViewPager) this.findViewById(R.id.viewpager_welcome);
        adapter = new WelcomePagerAdapter(imgList,this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                arrRadioButton[position].setChecked(true);
                if (position == imgList.size()-1){
                    radio_container.setVisibility(View.INVISIBLE);
                }else{
                    radio_container.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initDot() {
        radio_container = (RadioGroup) this.findViewById(R.id.radiogroup_welcome);
        arrRadioButton = new RadioButton[imgList.size()];
        //将图片集合大小来实例化RadioButton
        for (int i = 0; i < imgList.size(); i++) {
            RadioButton radiobtn = new RadioButton(this);
            radiobtn.setButtonDrawable(R.drawable.splash_add_selector);
            arrRadioButton[i] = radiobtn;
            radio_container.addView(radiobtn);
        }
        //设置第一个radiobutton被勾选
        arrRadioButton[0].setChecked(true);
        //点中radiobutton时，选中ViewPager相应的图片
        radio_container.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < arrRadioButton.length; i++) {
                    if (arrRadioButton[i].getId() == checkedId) {
                        viewPager.setCurrentItem(i);
                    }
                }
            }
        });

    }
}
