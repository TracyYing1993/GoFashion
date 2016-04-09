package demo.gofashion.com.org.utils.netutils;

import android.view.View;
import android.widget.AbsoluteLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/3/22.
 *
 * 用于配置及显示图片加载后的图片工具类
 */
public class ImageLoaderUtils {

    public static DisplayImageOptions getDisplayImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//是否进行内存缓存
                .cacheOnDisk(true)//是否进行磁盘缓存
                .build();//构造生成DisplayImageOptions
        return options;
    }
    public static DisplayImageOptions getDisplayImageOptionsRounde() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//是否进行内存缓存
                .cacheOnDisk(true)//是否进行磁盘缓存
                .displayer(new RoundedBitmapDisplayer(25))
                .build();//构造生成DisplayImageOptions
        return options;
    }

    //设置圆形图片
    public static DisplayImageOptions getCircleDisplayImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//是否进行内存缓存
                .cacheOnDisk(true)//是否进行磁盘缓存
                .displayer(new RoundedBitmapDisplayer(250))
                .build();//构造生成DisplayImageOptions
        return options;
    }

    public static DisplayImageOptions getDisplayImageOptionsRoundeSmall() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//是否进行内存缓存
                .cacheOnDisk(true)//是否进行磁盘缓存
                .displayer(new RoundedBitmapDisplayer(15))
                .build();//构造生成DisplayImageOptions
        return options;
    }

    //该方法用于动态改变控件的位置
    public static void setAutoLocation(View view,int width,int height,int x,int y){
        AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(width,height,x,y);
        view.setLayoutParams(params);
    }

    public static String getFormatPrice(double price){
        DecimalFormat format = new DecimalFormat("0.0");
        String format1 = format.format(price);
        return "￥"+format1;
    }
}
