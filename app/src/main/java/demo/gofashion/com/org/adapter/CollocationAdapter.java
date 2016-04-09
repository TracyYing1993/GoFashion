package demo.gofashion.com.org.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;

import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.CollocationBean;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;

/**
 * Created by 杨裕森 on 2016-03-24.
 */
public class CollocationAdapter extends BaseAdapter {

    List<CollocationBean.DataEntity> list;

    HashMap<Integer,View> orderView= new HashMap<>();

    Context context;

    DisplayImageOptions options;
    public CollocationAdapter(Context context, List<CollocationBean.DataEntity> list){
        this.context = context;
        this.list= list;
        options = ImageLoaderUtils.getDisplayImageOptions();

    }




    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    CollocationHolder myHolder =null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if(orderView.get(position)==null){
                myHolder = new CollocationHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.fragment_collocation_item_layout,parent,false);
                myHolder.collocation_imageview = (ImageView) convertView.findViewById(R.id.collocation_imageview);
                myHolder.comment_count = (TextView) convertView.findViewById(R.id.comment_count);
                myHolder.like_count = (TextView) convertView.findViewById(R.id.like_count);

                orderView.put(position,convertView);

                convertView.setTag(myHolder);
            }else{
                convertView = orderView.get(position);
                myHolder = (CollocationHolder) convertView.getTag();

            }
          String ImgUrl =  list.get(position).getImg();
        ImageLoader.getInstance().displayImage(ImgUrl,myHolder.collocation_imageview,options);
        myHolder.comment_count.setText(list.get(position).getComment_count() + "");
        myHolder.like_count.setText(list.get(position).getLike_count()+"");


        return convertView;
    }

    class CollocationHolder{
            ImageView collocation_imageview;
            TextView comment_count;
            TextView like_count;


    }


}
