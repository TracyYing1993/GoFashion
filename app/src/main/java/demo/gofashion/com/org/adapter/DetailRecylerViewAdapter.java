package demo.gofashion.com.org.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.ClassifyItemBean;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;

/**
 * Created by Administrator on 2016/3/25.
 */
public class DetailRecylerViewAdapter extends RecyclerView.Adapter<DetailRecylerViewAdapter.MyHolder> {

    private List<ClassifyItemBean> list;

    public DetailRecylerViewAdapter(List<ClassifyItemBean> list) {
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item_cardview_layout, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        ClassifyItemBean classifyItemBean = list.get(position);
        holder.closeName.setText(classifyItemBean.getName());
        holder.closePirce.setText(classifyItemBean.getSale_price()+"");
        DisplayImageOptions options = ImageLoaderUtils.getDisplayImageOptions();
        ImageLoader.getInstance().displayImage(classifyItemBean.getUrlImg(),holder.closeImg,options);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private final ImageView closeImg;
        private final TextView closeName;
        private final TextView closePirce;
        private View itemView;
        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            closeImg = ((ImageView) itemView.findViewById(R.id.closeImg));
            closeName = ((TextView) itemView.findViewById(R.id.closeName));
            closePirce = ((TextView) itemView.findViewById(R.id.closePirce));
        }
    }
}
