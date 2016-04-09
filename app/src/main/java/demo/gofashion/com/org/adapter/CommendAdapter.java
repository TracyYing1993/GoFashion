package demo.gofashion.com.org.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.CommendBean;
import demo.gofashion.com.org.bean.ViewHolderParent;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;

/**
 * Created by Administrator on 2016/3/26.
 */
public class CommendAdapter extends BaseAdapter {
    private List<CommendBean.DataEntity> list = new ArrayList<>();
    private Context context;

    public CommendAdapter(Context context) {
        this.context = context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderCommend vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_commend_layout, null);
            vh = new ViewHolderCommend(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolderCommend) convertView.getTag();
        }

        intDataToUI(position, vh);


        return convertView;
    }

    private void intDataToUI(int position, ViewHolderCommend vh) {
        CommendBean.DataEntity data = this.list.get(position);
        if (data != null) {
            DisplayImageOptions options = ImageLoaderUtils.getDisplayImageOptionsRounde();
            //加载圆形图片
            ImageLoader.getInstance().displayImage(data.getHead_img(), vh.image_round_commend, options);
            vh.text_account_name.setText(data.getNick_name());
            vh.text_commend_content.setText(data.getInfo());
            vh.text_time_commend.setText(data.getCreate_time());
        }
    }


class ViewHolderCommend extends ViewHolderParent {

    @Bind(R.id.image_round_commend)
    ImageView image_round_commend;

    @Bind(R.id.text_account_name)
    TextView text_account_name;

    @Bind(R.id.text_commend_content)
    TextView text_commend_content;

    @Bind(R.id.text_time_commend)
    TextView text_time_commend;

    public ViewHolderCommend(View convertView) {
        super(convertView);
    }

}

    public void addData(List<CommendBean.DataEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void getRefreshData() {
        this.list.clear();
        notifyDataSetChanged();
    }
}
