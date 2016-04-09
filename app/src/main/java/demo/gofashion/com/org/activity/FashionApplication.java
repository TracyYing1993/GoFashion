package demo.gofashion.com.org.activity;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by Administrator on 2016/3/21.
 */
public class FashionApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //获得外部缓存空间
        long maxSize=Runtime.getRuntime().maxMemory();
        File dir = getExternalCacheDir();//获得外部缓存
        ImageLoaderConfiguration imageLoader = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800)// max width, max height，即保存的每个缓存文件的最大长宽
                /*设置缓存的详细信息，不配或按需选配*/
                .threadPoolSize(5) // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)//线程优先级，1~10优先级逐渐升高，10代表图片立即显示
                .memoryCache(new UsingFreqLimitedMemoryCache((int) maxSize / 16))//内存缓存
                .memoryCacheSize((int) maxSize / 16)//开辟16分之一内存缓存空间
                .discCacheSize((int) maxSize / 16)//开辟16分之一磁盘缓存空间
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())// 保存的时候的URI名称用MD5加密
                .diskCache(new UnlimitedDiskCache(dir))//配置自定义缓存目录
                .build();//开始构建
        ImageLoader.getInstance().init(imageLoader);//初始化组件


    }


}
