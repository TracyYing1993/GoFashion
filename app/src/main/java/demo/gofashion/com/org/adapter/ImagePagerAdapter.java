package demo.gofashion.com.org.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

/**
 * 这是一个装载View的PageAdapter
 */
public class ImagePagerAdapter extends PagerAdapter {

	private List<View> list;

	public ImagePagerAdapter(List<View> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		//设置成最大，使用户看不到边界
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// 返回true的时候就不会创建对象了，直接从缓存中获取
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		//对ViewPager页号求模取出View列表中要显示的项
		position %= list.size();
		if (position<0){
			position = list.size()+position;
		}
		ImageView view = (ImageView) list.get(position);
		//如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
		ViewParent vp =view.getParent();
		if (vp!=null){
			ViewGroup parent = (ViewGroup)vp;
			parent.removeView(view);
		}
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		//不要在这里调用removeView
		// ((ViewPager)container).removeView(list.get(position));
	}


}
