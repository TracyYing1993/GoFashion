package demo.gofashion.com.org.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.adapter.ImagePagerAdapter;
import demo.gofashion.com.org.bean.FashionCommunityBean;
import demo.gofashion.com.org.bean.FashionIndexBean;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by Administrator on 2016/3/24.
 */
public class RemenImageLoopView extends RelativeLayout implements
        OkHttpUtils.callBack {

    private static final String TAG = AdvertisementImageLoopView.class.getSimpleName();

    private Gson gson;

    private ImageView[] arrDot;// 用于存储小圆点

    private LinearLayout layout_dot;// 将创建好的小圆点添加到容器中

    private ViewPager view_pager_relative;// 可实现左右滑动的页面

    private List<View> listImage = new ArrayList<View>();// 用来存储Image

    private List<FashionCommunityBean.DataEntity.ConfigEntity> list = new ArrayList<FashionCommunityBean.DataEntity.ConfigEntity>();// 用于下载JSON字符串

    private ImagePagerAdapter adapter;// 处理图片的PagerAdapter

    public boolean isClick;// 用于判断是否点击了图片，true停止播放图片

    private static Context context;// 传入上下文

    private static Timer timer;// 创建一个定时器

    private Handler handler = new Handler() {// 实例化一个Handler用于实现图片的自动轮播效果
        public void handleMessage(android.os.Message msg) {
            view_pager_relative.setCurrentItem(msg.what);// 将每次收到的空消息累加成ViewPager的当前项
        }

        ;
    };
    private FashionCommunityBean fashionBean;

    private String url;

    public RemenImageLoopView(Context context,String url) {// 这个构造方法用于在代码中New出来时调用
        super(context);
        this.context = context;
        this.url=url;
        init();// 初始化并找到控件

    }

    public RemenImageLoopView(Context context, AttributeSet attrs) {// 这个构造方法用于在Xml文件中生成时调用
        super(context, attrs);
        this.context = context;
        init();// 初始化并找到控件
    }


    private void init() {
        //注册广播
     /*   adverReceiver = new AdverReceiver();
        context.registerReceiver(adverReceiver,new IntentFilter("demo.newshui.com.org.broadcast"));
*/
        gson = new Gson();
        // 填充布局
        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.advertisement_image_layout, this, true);

        // 找到相关控件
        layout_dot = (LinearLayout) view.findViewById(R.id.dot_layout);

        view_pager_relative = (ViewPager) view
                .findViewById(R.id.view_pager_relative);

        download();
    }

    private void download() {
        // 下载Json数据并解析,开启一个线程进行下载
        if(!TextUtils.isEmpty(url)) {
            OkHttpUtils.callBackUIDataFormatOne(url,
                    OkHttpUtils.TYPE_TEXT, RemenImageLoopView.this);
        }else{
            Log.e(TAG, "download: 路径不对");
        }
    }

    /**
     * <p/>
     * 解析出图片的路径后，在进行图片下载
     * <p/>
     * 遍历List<CbkIndexData>集合取出来里的图片路径，用于异步加载图片
     */
    private void downloadImg() {

        for (int i = 0; i < list.size(); i++) {// 遍历集合

            ImageView img = new ImageView(context);

            // 设置比例
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // 设置参数
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            img.setLayoutParams(params);
            // 添加监听事件,点击后isClick返回true就停止轮播图片
            img.setTag(i);
            img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isClick) {
                        timer.cancel();
                        timer = null;
                        isClick = false;
                    }


                }
            });
            //从图片加载器中获得图片
            DisplayImageOptions options = ImageLoaderUtils.getDisplayImageOptions();
            //拿出图片路径
            String img1 = list.get(i).getImg();
            if (!TextUtils.isEmpty(img1)) {
                ImageLoader.getInstance().displayImage(img1, img, options);
                listImage.add(img);
            }

        }
        initDot();// 初始化小圆点
        startLoopImg();// 轮播图片
        // 设置ViewPager属性
        setViewPager();
    }

    /**
     * 启动轮播图片的方法
     */
    public void startLoopImg() {
        if (timer != null) {// 判断定时器是否为空
            return;
        }
        if (isClick == false) {// 是否点击了图片

            timer = new Timer();// 初始化定时器

            timer.schedule(new TimerTask() {// 定时任务

                private int index;

                @Override
                public void run() {
                    // 给Handler发送空消息

                    handler.sendEmptyMessage(index++);

                    isClick = true;
                }
            }, 0, 2000);
        }
    }

    /**
     * 根据图片的数量来 初始化小圆点
     */
    private void initDot() {
        arrDot = new ImageView[list.size()];
        for (int i = 0; i < arrDot.length; i++) {

            ImageView img = new ImageView(context);
            // 设置参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            // 设置边距
            params.leftMargin = 8;
            params.rightMargin = 13;

            img.setLayoutParams(params);

            // 设置图片的比例类型
            img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            // 将生成好的selector（选择器）样式设置给img
            img.setBackgroundResource(R.drawable.dot_style);
            // 设置为不可用状态
            img.setEnabled(false);

            arrDot[i] = img;// 添加到数组中

            layout_dot.addView(img);// 添加到窗口容器中
        }
        // 设置第一个为选中状态
        layout_dot.getChildAt(0).setEnabled(true);
    }

    private void setViewPager() {
        // 实例化构造器

        adapter = new ImagePagerAdapter(listImage);

        // 设置构造器
        view_pager_relative.setAdapter(adapter);

        // 添加页码改变的监听事件

        view_pager_relative.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // 进行对页码的position求模处理,小圆点和title所对应图片的position
                int a = 0;
                a = position % listImage.size();

                for (int i = 0; i < arrDot.length; i++) {
                    arrDot[i].setEnabled(false);
                }
                arrDot[a].setEnabled(true);
            }

            @Override
            public void onPageScrolled(int position, float positionOff,
                                       int positionOffPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void callBackUIString(String data) {
        if (data != null) {// 判断数据是否为空
            // 不为空，就用Gson进行Json解析
            fashionBean = gson.fromJson(data, FashionCommunityBean.class);
            //将解析好后的数据给集合
            List<FashionCommunityBean.DataEntity.ConfigEntity> config = fashionBean.getData().get(0).getConfig();
            if (config != null) {
                this.list.addAll(config);
            }
            if (this.list != null && this.list.size() > 0) {
                for (FashionCommunityBean.DataEntity.ConfigEntity entity : this.list) {
                    Log.e(TAG, "getData: " + entity);
                }
                downloadImg();// 解析出图片的路径后，在进行图片下载
            }


        }
    }

    @Override
    public void callBackUIByte(byte[] datas) {

    }


}

