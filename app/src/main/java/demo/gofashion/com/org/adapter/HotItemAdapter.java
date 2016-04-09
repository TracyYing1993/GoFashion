package demo.gofashion.com.org.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import demo.gofashion.com.org.activity.HotItemActivity;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.HotItemBean;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by Administrator on 2016/3/24.
 */
public class HotItemAdapter extends BaseAdapter{

    private List<HotItemBean> hotList;
    private String url;
    private String category;
    private Gson gson;
    private String temp_id;


    public HotItemAdapter(List<HotItemBean> hotList) {
        this.hotList = hotList;

    }

    @Override
    public int getCount() {
        return hotList.size();
    }

    @Override
    public Object getItem(int position) {
        return hotList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        HotHolder hotHolder;
        if (convertView == null){
            hotHolder = new HotHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotgridview_item_layout,null,false);
            hotHolder.name = (TextView) convertView.findViewById(R.id.gridview_item_name);
            hotHolder.info = (TextView) convertView.findViewById(R.id.gridview_item_info);
            hotHolder.show_img = (ImageView) convertView.findViewById(R.id.gridview_item_img);

            convertView.setTag(hotHolder);
        }else{
            hotHolder = (HotHolder) convertView.getTag();
        }
        final HotItemBean hotItemBean = hotList.get(position);

        hotHolder.name.setText(hotItemBean.getName());
        hotHolder.info.setText(hotItemBean.getInfo());
        DisplayImageOptions displayImageOptions = ImageLoaderUtils.getDisplayImageOptions();

        ImageLoader.getInstance().displayImage(hotItemBean.getImgUrl(),hotHolder.show_img,displayImageOptions);
        hotHolder.show_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp_id = hotItemBean.getTemp_id();
                Intent intent = new Intent(parent.getContext(), HotItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("temp_id", temp_id);
                bundle.putString("title",hotItemBean.getName());
                intent.putExtras(bundle);
                parent.getContext().startActivity(intent);

            }
        });
        return convertView;
    }


    class HotHolder {
        private TextView name;
        private TextView info;
        private ImageView show_img;
    }
}
