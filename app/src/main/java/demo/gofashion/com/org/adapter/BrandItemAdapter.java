package demo.gofashion.com.org.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import demo.gofashion.com.org.activity.ClassifyDetailActivity;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.BrandItemBean;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;

/**
 * Created by Administrator on 2016/3/24.
 */
public class BrandItemAdapter extends BaseAdapter {
    private List<BrandItemBean> brandList;


    public BrandItemAdapter(List<BrandItemBean> brandList) {
        this.brandList = brandList;

    }

    @Override
    public int getCount() {
        return brandList.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return brandList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        BrandHolder brandHolder;
        if (convertView == null){
            brandHolder = new BrandHolder();

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.brandgridview_item_layout,null,false);
            brandHolder.imageView = (ImageView) convertView.findViewById(R.id.branditem_img);
            convertView.setTag(brandHolder);
        }else{
            brandHolder = (BrandHolder) convertView.getTag();
        }
        if (position != getCount()-1){
            final BrandItemBean brandItemBean = brandList.get(position);
            DisplayImageOptions options = ImageLoaderUtils.getDisplayImageOptions();
            ImageLoader.getInstance().displayImage(brandItemBean.getImgurl(),brandHolder.imageView,options);
            brandHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(parent.getContext(), ClassifyDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("brandItem",brandItemBean);
                    intent.putExtras(bundle);
                    parent.getContext().startActivity(intent);
                }
            });
        }else if(position == getCount()-1){
            brandHolder.imageView.setImageResource(R.drawable.more_brand);
            brandHolder.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            brandHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(parent.getContext(), "没有更多", Toast.LENGTH_SHORT).show();
                }
            });
        }


        return convertView;
    }

    class BrandHolder{
        private ImageView imageView;
    }

}
