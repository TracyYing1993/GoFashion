package demo.gofashion.com.org.bean;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/24.
 * 用于对控件绑定的超类
 */
public class ViewHolderParent {
    public View convertView;
    public ViewHolderParent(View convertView){
        this.convertView=convertView;
        ButterKnife.bind(this,convertView);
    }
}
