package demo.gofashion.com.org.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.util.List;

import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.CollocationItemBean;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;

/**
 * Created by 杨裕森 on 2016-03-25.
 */
public class CollocationItemAdapter extends BaseAdapter {
    List<CollocationItemBean> list;


    Context context;
    DisplayImageOptions options;

    public CollocationItemAdapter(List<CollocationItemBean> list, Context context) {
        this.list = list;
        this.context = context;
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

    MyHolder myholder = null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            myholder = new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_collocation_clickitem_format1_layout, parent, false);
            myholder.big_imageview = (ImageView) convertView.findViewById(R.id.big_imageview);
            myholder.designer_img = (ImageView) convertView.findViewById(R.id.designer_img);
            myholder.concernedCount = (TextView) convertView.findViewById(R.id.concernedCount);
            myholder.designer_nick_name = (TextView) convertView.findViewById(R.id.designer_nick_name);
            myholder.product_list_brand_value = (TextView) convertView.findViewById(R.id.product_list_brand_value);
            myholder.tab_str = (TextView) convertView.findViewById(R.id.tab_str);

            myholder.product_img = (ImageView) convertView.findViewById(R.id.product_img);
            myholder.spec_price_list = (TextView) convertView.findViewById(R.id.spec_price_list);
            myholder.cate_value = (TextView) convertView.findViewById(R.id.cate_value);
            myholder.like_count = (TextView) convertView.findViewById(R.id.like_count);
            myholder.user_icon_array = (LinearLayout) convertView.findViewById(R.id.user_icon_array);



            convertView.setTag(myholder);
        } else {
            myholder = (MyHolder) convertView.getTag();
        }

        String img = list.get(0).getData().getImg();
        ImageLoader.getInstance().displayImage(img, myholder.big_imageview, options);

        myholder.designer_nick_name.setText("#"+list.get(0).getData().getDesigner().getNick_name());
        String imgurl = list.get(0).getData().getDesigner().getHead_img();
        ImageLoader.getInstance().displayImage(imgurl, myholder.designer_img, options);

        myholder.concernedCount.setText(list.get(0).getData().getDesigner().getConcernedCount());
        myholder.product_list_brand_value.setText(list.get(0).getData().getProduct_list().get(0).getBrand_value());

        myholder.tab_str.setText(list.get(0).getData().getTab_str());
        Log.e("MainActivity", "tab_src" + list.get(0).getData().getTab_str());

        String product_img = list.get(0).getData().getProduct_list().get(0).getProduct_img();
        ImageLoader.getInstance().displayImage(product_img, myholder.product_img, options);

        //价格
        String price = list.get(0).getData().getProduct_list().get(0).getPrice();
        DecimalFormat format = new DecimalFormat("0.0");
        String format1 = format.format(Double.parseDouble(price));
        myholder.spec_price_list.setText(format1);

        myholder.cate_value.setText(list.get(0).getData().getProduct_list().get(0).getCate_value());

        myholder.like_count.setText("喜欢"+"("+list.get(0).getData().getLike_count()+")");

        List<CollocationItemBean.DataEntity.Like_user_listEntity> like_user_list = list.get(0).getData().getLike_user_list();
        for(int i=0;i<like_user_list.size();i++){
            String head_img = like_user_list.get(i).getHead_img();
            ImageView imageview = new ImageView(context);
            imageview.setBackgroundResource(R.drawable.pic_shape);
            imageview.setMaxWidth(50);
            imageview.setMaxHeight(50);

            ImageLoader.getInstance().displayImage(head_img,imageview,options);

            myholder.user_icon_array.addView(imageview);

        }


        return convertView;
    }

    class MyHolder {
        ImageView big_imageview;
        ImageView designer_img;
        TextView designer_nick_name;
        TextView concernedCount;
        TextView cate_value;
        ImageView product_img;
        TextView spec_price_list;

        TextView product_list_brand_value;
        TextView tab_str;

        TextView like_count;
        LinearLayout user_icon_array;
    }



}
