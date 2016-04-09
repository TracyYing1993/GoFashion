package demo.gofashion.com.org.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.HashMap;
import java.util.List;

import demo.gofashion.com.org.activity.Collocation_ClickItem_Activity;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.ClickMoreBean;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;

public class ClickMoreAdapter extends BaseAdapter {
    List<ClickMoreBean.DataEntity> dataEntity;
    HashMap<Integer,View> orderview =new HashMap<>();
    Context context;


    DisplayImageOptions options;
    public ClickMoreAdapter( List<ClickMoreBean.DataEntity> dataEntity,Context context){
        this.dataEntity = dataEntity;
        this.context =context;
        options = ImageLoaderUtils.getDisplayImageOptions();

    }

    @Override
    public int getCount() {
        return dataEntity.size();
    }

    @Override
    public Object getItem(int position) {
        return dataEntity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    MyHolder myholder =null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(orderview.get(position)==null){
            myholder = new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.click_item_more_layout,parent,false);
            myholder.linear_array_img  = (LinearLayout) convertView.findViewById(R.id.linear_array_img);
            myholder.user_img = (ImageView) convertView.findViewById(R.id.user_img);
            myholder.user_title = (TextView) convertView.findViewById(R.id.user_title);
            myholder.user_content = (TextView) convertView.findViewById(R.id.user_content);
            orderview.put(position,convertView);
            convertView.setTag(myholder);

        }else{
            convertView=orderview.get(position);
           myholder = (MyHolder) convertView.getTag();
        }

        ClickMoreBean.DataEntity dataEntity = this.dataEntity.get(position);
        ImageLoader.getInstance().displayImage(dataEntity.getImg(),myholder.user_img,options);
        myholder.user_title.setText("#" + dataEntity.getName());
        myholder.user_content.setText(dataEntity.getInfo());

        List<ClickMoreBean.DataEntity.Collocation_listEntity> collocation_list = dataEntity.getCollocation_list();
        myholder.linear_array_img.removeAllViews();
        for(int i=0;i<collocation_list.size();i++){
            String img = collocation_list.get(i).getImg();
            final String id =  collocation_list.get(i).getId();
            ImageView imageview = new ImageView(context);
            imageview.setMaxHeight(170);
            imageview.setMaxWidth(150);
            imageview.setPadding(0, 0, 10, 0);
          //  imageview.setScaleType(ImageView.ScaleType.FIT_XY);


            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Collocation_ClickItem_Activity.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });

            ImageLoader.getInstance().displayImage(img, imageview, options);

            myholder.linear_array_img.addView(imageview);


        }


        return convertView;
    }
    class MyHolder{
        ImageView user_img;
        TextView user_title;
        TextView user_content;
        LinearLayout linear_array_img;

    }


}
