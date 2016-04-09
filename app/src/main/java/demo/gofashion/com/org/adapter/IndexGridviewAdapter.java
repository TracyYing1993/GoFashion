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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.IndexGirdviewBean;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;

public class IndexGridviewAdapter extends BaseAdapter {

    List<IndexGirdviewBean.DataEntity.ProductListEntity> data = new ArrayList<>();
    HashMap<Integer, View> orderview = new HashMap<>();
    DisplayImageOptions options;
    Context context;

    public IndexGridviewAdapter(Context context) {
        this.context = context;
        options = ImageLoaderUtils.getDisplayImageOptions();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    GridViewHolder gHolder = null;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IndexGirdviewBean.DataEntity.ProductListEntity productListEntity = data.get(position);
        if (orderview.get(position) == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_home_gridview_item, null);
            gHolder = new GridViewHolder();
            gHolder.tagUrl_pic = (ImageView) convertView.findViewById(R.id.tagUrl_pic);
            gHolder.brandUrl_pic = (ImageView) convertView.findViewById(R.id.brandUrl_pic);
            gHolder.producturl_pic = (ImageView) convertView.findViewById(R.id.producturl_pic);
            gHolder.market_price = (TextView) convertView.findViewById(R.id.market_price);
            gHolder.product_name = (TextView) convertView.findViewById(R.id.product_name);
            gHolder.spec_price_list = (TextView) convertView.findViewById(R.id.spec_price_list);
            gHolder.text_line = ((TextView) convertView.findViewById(R.id.text_line));
            orderview.put(position, convertView);
            convertView.setTag(gHolder);
        } else {
            convertView = orderview.get(position);
            gHolder = (GridViewHolder) convertView.getTag();
        }

        if (productListEntity.getProdClsTag().size() != 0) {
            gHolder.tagUrl_pic.setVisibility(View.VISIBLE);
            String tagUrl = productListEntity.getProdClsTag().get(0).getTagUrl();
            ImageLoader.getInstance().displayImage(tagUrl, gHolder.tagUrl_pic, options);
        } else {
            gHolder.tagUrl_pic.setImageResource(R.mipmap.ic_launcher);
            gHolder.tagUrl_pic.setVisibility(View.GONE);
        }
        ImageLoader.getInstance().displayImage(productListEntity.getBrandUrl(), gHolder.brandUrl_pic, options);
        ImageLoader.getInstance().displayImage(productListEntity.getProduct_url(), gHolder.producturl_pic, options);

        gHolder.product_name.setText(productListEntity.getProduct_name());
        //原价
        DecimalFormat format = new DecimalFormat("0.0");
        String format1 = format.format(Double.parseDouble(productListEntity.getMarket_price()));

        String format2 = format.format(Double.parseDouble(productListEntity.getSpec_price_list().get(0)));
        if (format1.equals(format2)) {
            gHolder.market_price.setText("￥" + format1);
            gHolder.spec_price_list.setVisibility(View.GONE);
            gHolder.text_line.setVisibility(View.GONE);
        } else {
            gHolder.spec_price_list.setVisibility(View.VISIBLE);
            gHolder.text_line.setVisibility(View.VISIBLE);

            gHolder.market_price.setText("￥" + format1);
            //折扣价
            gHolder.spec_price_list.setText("￥" + format2);
        }

        return convertView;
    }

    public void addData(List<IndexGirdviewBean.DataEntity.ProductListEntity> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void getRefreshData() {
        this.data.clear();
        notifyDataSetChanged();
    }


    class GridViewHolder {
        ImageView tagUrl_pic;
        ImageView brandUrl_pic;
        ImageView producturl_pic;
        TextView product_name;
        //折扣价
        TextView spec_price_list;
        //原价
        TextView market_price;

        //杆
        TextView text_line;

    }


}
