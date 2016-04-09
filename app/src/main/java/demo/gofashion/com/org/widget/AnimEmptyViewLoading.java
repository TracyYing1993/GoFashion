package demo.gofashion.com.org.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import demo.gofashion.com.org.activity.R;

/**
 * Created by Administrator on 2016/3/25.
 * ListView的emptyview的加载动画
 */
public class AnimEmptyViewLoading extends RelativeLayout {
    private Context context;
    private ImageView img;
    public AnimEmptyViewLoading(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public AnimEmptyViewLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.anim_emptyview_loading_layout,this,true);
        img = (ImageView) view.findViewById(R.id.anim_loading_img);
        img.setBackgroundResource(R.drawable.anim_emptyview_loading);
        AnimationDrawable anim = (AnimationDrawable) img.getBackground();
        anim.start();
    }
}
