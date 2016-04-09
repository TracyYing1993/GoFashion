package demo.gofashion.com.org.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.Bind;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.ViewHolderParent;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;

/**
 * Created by Administrator on 2016/3/26.
 */
public class CollectBaseAdapter extends BaseAdapter {
    private static final String TAG = CollectBaseAdapter.class.getSimpleName();
    private List<Map<String, Object>> list ;
    private Context context;

    public CollectBaseAdapter(List<Map<String, Object>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderCollect vh;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.collect_item_layout,null);
            vh=new ViewHolderCollect(convertView);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolderCollect) convertView.getTag();
        }
        initDataToUI(position,vh);
        return convertView;
    }

    private void initDataToUI(int position, ViewHolderCollect vh) {
        Map<String, Object> map = this.list.get(position);
        for (int i = 0; i < map.size(); i++) {
            Log.e(TAG, "initDataToUI: "+map.get(i));
        }
        if(map!=null) {
            String price= (String) map.get("price");
            vh.item_text_price.setText("￥" + ImageLoaderUtils.getFormatPrice(Double.parseDouble(price)));
            String product_name= (String) map.get("product_name");
            vh.item_text_product_name.setText(product_name);
            byte[] arrBitmap = (byte[]) map.get("bitmap");
            Bitmap bitmap = BitmapFactory.decodeByteArray(arrBitmap, 0, arrBitmap.length);
            vh.item_image_showMain.setImageBitmap(bitmap);
        }
    }

    class ViewHolderCollect extends ViewHolderParent{

        @Bind(R.id.item_text_price)
        TextView item_text_price;

        @Bind(R.id.item_text_product_name)
        TextView item_text_product_name;

        @Bind(R.id.item_image_showMain)
        ImageView item_image_showMain;

        public ViewHolderCollect (View convertView) {
            super(convertView);
        }
    }
}
