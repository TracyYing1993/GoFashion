package demo.gofashion.com.org.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/3/24.
 */
public class SelfScrollListView extends ListView {
    public SelfScrollListView(Context context) {
        super(context);
    }

    public SelfScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //使其高度，用最大的值向右位移2位
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }
}
