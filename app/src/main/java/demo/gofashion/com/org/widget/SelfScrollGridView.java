package demo.gofashion.com.org.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/3/24.
 */
public class SelfScrollGridView extends GridView {
    public SelfScrollGridView(Context context) {
        super(context);
    }

    public SelfScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfScrollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);

    }
}
