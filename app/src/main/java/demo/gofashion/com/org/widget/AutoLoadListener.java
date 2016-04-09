package demo.gofashion.com.org.widget;


import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/3/24.
 * <p/>
 * 实现OnScrollListener接口
 * 滚动到列表底部，加载下一页的数据
 */
public class AutoLoadListener implements OnScrollListener{

    private static final String TAG = AutoLoadListener.class.getSimpleName();



    private AutoLoadCallBack mCallback;//创建一个自动加载数据的回调接口

    public AutoLoadListener(AutoLoadCallBack callBack) {
        this.mCallback=callBack;
    }


    public interface AutoLoadCallBack {
        void execute();//该方法用于执行
    }

    private int getLastVisiblePosition;//最后一个显示的绝对位置

    private int lastVisiblePositionY;//最后一个显示的绝对位置的Y坐标


    //覆写当滚动状态改变时和滚动的方法
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if(scrollState==OnScrollListener.SCROLL_STATE_IDLE){//滚动停止的状态
            //判断是否滚动到了底部
            if(view.getLastVisiblePosition()==(view.getCount()-1)){
                View v=(View)view.getChildAt(view.getChildCount()-1);
                int[] location =new int[2];
                v.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
                int y=location[1];

                Log.e(TAG, "onScrollStateChanged: x:"+location[0]+"/y:"+location[1] );

                if(view.getLastVisiblePosition() !=getLastVisiblePosition&&lastVisiblePositionY!=y)//第一次拖至底部
                {
                    Toast.makeText(view.getContext(), "已经拖动至底部，再次拖动即可翻页", Toast.LENGTH_SHORT).show();

                    lastVisiblePositionY=y;
                    return;
                }else if(view.getLastVisiblePosition()==getLastVisiblePosition&&
                        lastVisiblePositionY==y){
                    //第二次拖至底部
                    mCallback.execute();//执行方法中的逻辑代码
                }
            }

            //未滚动到底部，第二次拖动至底部都初始化
            getLastVisiblePosition=0;
            lastVisiblePositionY=0;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
