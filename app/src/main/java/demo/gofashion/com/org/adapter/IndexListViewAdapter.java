package demo.gofashion.com.org.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import demo.gofashion.com.org.activity.IndexWebView;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.FashionIndexBean;
import demo.gofashion.com.org.bean.ViewHolderParent;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;
import demo.gofashion.com.org.widget.AdvertisementImageLoopView;

/**
 * Created by Administrator on 2016/3/24.
 */
public class IndexListViewAdapter extends BaseAdapter {

    private static final String TAG = IndexListViewAdapter.class.getSimpleName();
    private List<FashionIndexBean.DataEntity> list = new ArrayList<>();
    private Context context;
    private HashMap<Integer, View> returnMapView = new HashMap<>();

    public IndexListViewAdapter(Context context) {
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

    ViewHolderParent holderParent;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FashionIndexBean.DataEntity dataEntity = this.list.get(position);
        if (returnMapView.get(position) == null) {
            //拿出类型
            int type = Integer.parseInt(dataEntity.getType());
            if (type == 9) {
                //广告轮播
                Log.e(TAG, "getView: type:" + type);
                convertView = new AdvertisementImageLoopView(context, UrlUtils.INDEX_PATH);
                holderParent = new ViewHolderType9(convertView);
                convertView.setTag(holderParent);

                returnMapView.put(position, convertView);
            } else if (type == 101) {
                Log.e(TAG, "getView: type:" + type);
                //不规则图形布局
                convertView = LayoutInflater.from(context).inflate(R.layout.item_type101_data_layout, parent, false);
                holderParent = new ViewHolderType101(convertView);
                convertView.setTag(holderParent);
                returnMapView.put(position, convertView);
            } else if (type == 99999) {
                Log.e(TAG, "getView: type:" + type);

                convertView = LayoutInflater.from(context).inflate(R.layout.item_type101_data_layout, parent, false);
                holderParent = new ViewHolderType99999(convertView);
                convertView.setTag(holderParent);
                returnMapView.put(position, convertView);
            } else if (type == 8) {
                Log.e(TAG, "getView: type:" + type);

                convertView = LayoutInflater.from(context).inflate(R.layout.item_type101_data_layout, parent, false);
                holderParent = new ViewHolderType8(convertView);
                convertView.setTag(holderParent);
                returnMapView.put(position, convertView);
            } else {
                Log.e(TAG, "getView: type:" + type);
                convertView = LayoutInflater.from(context).inflate(R.layout.item_type101_data_layout, parent, false);
                holderParent = new ViewHolderType99999(convertView);
                convertView.setTag(holderParent);
                returnMapView.put(position, convertView);
            }

        } else {
            convertView = returnMapView.get(position);
            holderParent = (ViewHolderParent) convertView.getTag();
        }
        initRefreshViewToUi(dataEntity, position, holderParent);
        return convertView;
    }

    private void initRefreshViewToUi(FashionIndexBean.DataEntity dataEntity, int position, ViewHolderParent holderParent) {
        int type = Integer.parseInt(dataEntity.getType());
        //拿出Config中的数据
        List<FashionIndexBean.DataEntity.ConfigEntity> config = dataEntity.getConfig();

        DisplayImageOptions options = ImageLoaderUtils.getDisplayImageOptions();
        if (type == 101) {
            //初始化控件ui
            initViewHolderUi101((ViewHolderType101) holderParent, position, options, config);
        } else if (type == 99999) {
            initViewHolderUi99999(dataEntity, (ViewHolderType99999) holderParent, config);
        } else if (type == 8) {
            initViewHolderUi8((ViewHolderType8) holderParent, config, options);
        }

    }

    private void initViewHolderUi8(ViewHolderType8 holderParent, final List<FashionIndexBean.DataEntity.ConfigEntity> config, DisplayImageOptions options) {
        //向下转型
        ViewHolderType8 type8 = holderParent;
        if (config != null) {
            final FashionIndexBean.DataEntity.ConfigEntity configEntity = config.get(0);
            if (configEntity != null) {

                ImageView imageView = new ImageView(context);
                Log.e(TAG, "initViewHolderUi101: 进来了 ");
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(Integer.parseInt(configEntity.getImg_width()),
                        Integer.parseInt(configEntity.getImg_height()));
                imageView.setLayoutParams(params);
                ImageLoader.getInstance().displayImage(configEntity.getImg(), imageView, options);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = configEntity.getUrl();
                        String title = configEntity.getName();
                        Bundle bundle = new Bundle();
                        bundle.putString("url",url+configEntity.getJump_type());
                        bundle.putString("title",title);
                        context.startActivity(new Intent(context, IndexWebView.class).putExtras(bundle));
                    }
                });
                type8.absolute_container.addView(imageView);
            }
        }
    }

    private void initViewHolderUi99999(FashionIndexBean.DataEntity dataEntity, ViewHolderType99999 holderParent, List<FashionIndexBean.DataEntity.ConfigEntity> config) {
        if (config != null) {
            ViewHolderType99999 vh = holderParent;
            ViewGroup.LayoutParams layoutParams = vh.convertView.getLayoutParams();
            FashionIndexBean.DataEntity.ConfigEntity configEntity = dataEntity.getConfig().get(0);
            if (configEntity != null) {
                layoutParams.width = Integer.parseInt(configEntity.getImg_width());
                layoutParams.height = Integer.parseInt(dataEntity.getConfig().get(0).getImg_height());
                vh.convertView.setLayoutParams(layoutParams);
            }
        }
    }

    private void initViewHolderUi101(ViewHolderType101 holderParent, int position, DisplayImageOptions options, List<FashionIndexBean.DataEntity.ConfigEntity> config) {

        //向下转型
        ViewHolderType101 type101 = holderParent;
        if (config != null) {
            FashionIndexBean.DataEntity.ConfigEntity configEntity = config.get(0);
            final List<FashionIndexBean.DataEntity.ConfigEntity.SubConfigEntity> config1 = configEntity.getConfig();
            if (config1 != null) {
                for (int i = 0; i < config1.size(); i++) {
                    ImageView imageView = new ImageView(context);
                    Log.e(TAG, "initViewHolderUi101: 进来了 ");
                    final FashionIndexBean.DataEntity.ConfigEntity.SubConfigEntity subConfigEntity = config1.get(i);
                    ImageLoaderUtils.setAutoLocation(imageView, Integer.parseInt(subConfigEntity.getWidth()),
                            Integer.parseInt(subConfigEntity.getHeight()),
                            Integer.parseInt(subConfigEntity.getX()),
                            Integer.parseInt(subConfigEntity.getY()));
                    ImageLoader.getInstance().displayImage(subConfigEntity.getImg(), imageView, options);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //到详情页
                            FashionIndexBean.DataEntity.ConfigEntity.SubConfigEntity.JumpEntity jump = subConfigEntity.getJump();
                            if(jump!=null){
                                //取出url
                                String url = jump.getUrl();
                                String title = jump.getName();
                                Bundle bundle = new Bundle();
                                bundle.putString("url",url+jump.getJump_type());
                                bundle.putString("title",title);
                                context.startActivity(new Intent(context, IndexWebView.class).putExtras(bundle));
                            }
                        }
                    });
                    type101.absolute_container.addView(imageView);
                }
            }
        }
    }

    class ViewHolderType9 extends ViewHolderParent {

        public ViewHolderType9(View convertView) {
            super(convertView);
        }
    }

    class ViewHolderType101 extends ViewHolderParent {

        @Bind(R.id.absolute_container)
        AbsoluteLayout absolute_container;

        public ViewHolderType101(View convertView) {
            super(convertView);
        }
    }

    class ViewHolderType8 extends ViewHolderParent {

        @Bind(R.id.absolute_container)
        AbsoluteLayout absolute_container;

        public ViewHolderType8(View convertView) {
            super(convertView);
        }
    }

    class ViewHolderType99999 extends ViewHolderParent {
        public ViewHolderType99999(View convertView) {
            super(convertView);
        }

    }

    public void addData(List<FashionIndexBean.DataEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void getRefreshData(){
        this.list.clear();
        notifyDataSetChanged();
    }
}
