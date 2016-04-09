package demo.gofashion.com.org.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import demo.gofashion.com.org.activity.R;
import demo.gofashion.com.org.bean.FashionCommunityBean;
import demo.gofashion.com.org.bean.FashionIndexBean;
import demo.gofashion.com.org.bean.ViewHolderParent;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;
import demo.gofashion.com.org.widget.RemenImageLoopView;

/**
 * Created by Administrator on 2016/3/24.
 */
public class RemenListViewAdapter extends BaseAdapter {
    private static final String TAG = RemenListViewAdapter.class.getSimpleName();
    private List<FashionCommunityBean.DataEntity> list = new ArrayList<>();
    private Context context;
    private HashMap<Integer, View> map15 = new HashMap<>();
    private HashMap<Integer, View> map14 = new HashMap<>();
    private HashMap<Integer, View> mapView = new HashMap<>();
    private boolean isFirst14 = true;
    private boolean isFirst15 = true;

    public RemenListViewAdapter(Context context) {
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
        Log.e(TAG,"getView"+"调用了几次");
        FashionCommunityBean.DataEntity dataEntity = this.list.get(position);
        if (mapView.get(position) == null) {
            //拿出类型
            int type = Integer.parseInt(dataEntity.getType());
            if (type == 9) {  //热门滚动图
                Log.e(TAG, "getView: type:" + type);
                convertView = new RemenImageLoopView(context, UrlUtils.HOT);
                holderParent = new HolderRemenType9(convertView);
                convertView.setTag(holderParent);
                mapView.put(position, convertView);
            } else if (type == 15) {  //热门话题
                Log.e(TAG, "getView: type:" + type);
                convertView = LayoutInflater.from(context).inflate(R.layout.shequ_remen_type14, parent, false);
                holderParent = new HolderRemenType15(convertView);
                convertView.setTag(holderParent);
                mapView.put(position, convertView);
            } else if (type == 99999) {  //间隔
                Log.e(TAG, "getView: type:" + type);
                convertView = LayoutInflater.from(context).inflate(R.layout.item_type101_data_layout, parent, false);
                holderParent = new HolderRemenType99999(convertView);
                convertView.setTag(holderParent);
                mapView.put(position, convertView);
            } else if (type == 14) {  //时尚达人
                Log.e(TAG, "getView: type:" + type);
                convertView = LayoutInflater.from(context).inflate(R.layout.shequ_remen_type14, parent, false);
            holderParent = new HolderRemenType14(convertView);
                convertView.setTag(holderParent);
                mapView.put(position, convertView);
            } else {
                Log.e(TAG, "getView: type:" + type);
                convertView = LayoutInflater.from(context).inflate(R.layout.item_type101_data_layout, parent, false);
                holderParent = new HolderRemenType99999(convertView);
                convertView.setTag(holderParent);
                mapView.put(position, convertView);
            }

        } else {
            convertView = mapView.get(position);
            holderParent = (ViewHolderParent) convertView.getTag();
        }
        setViewToUI(dataEntity, position, holderParent);
        return convertView;
    }

    private void setViewToUI(FashionCommunityBean.DataEntity dataEntity, int position, ViewHolderParent holderParent) {
        int type = Integer.parseInt(dataEntity.getType());
        //拿出Config中的数据
        List<FashionCommunityBean.DataEntity.ConfigEntity> config = dataEntity.getConfig();

        DisplayImageOptions options = ImageLoaderUtils.getDisplayImageOptions();

        if (type == 15) {
            //初始化控件ui
            initViewHolderUI15((HolderRemenType15) holderParent, position, options, config);
        } else if (type == 99999) {
            initViewHolderUI99999(dataEntity, (HolderRemenType99999) holderParent, config);
        } else if (type == 14) {
            initViewHolderUI14((HolderRemenType14) holderParent, options, config);

        }
    }

    private void initViewHolderUI15(HolderRemenType15 holderParent, int position, DisplayImageOptions options, List<FashionCommunityBean.DataEntity.ConfigEntity> config) {
        //向下转型
        HolderRemenType15 type15 = holderParent;
        if (config != null) {

            for (int i = 0; i < config.size(); i++) {
                Log.e(TAG, "------------------------------>" + config.size());
                View view = LayoutInflater.from(context).inflate(R.layout.item_type15_data_layout,null);
                TextView text_left = (TextView) view.findViewById(R.id.type15_text_left);
                TextView text_right = (TextView) view.findViewById(R.id.type15_text_right);
                ImageView imageview1 = (ImageView) view.findViewById(R.id.type15_imageview1);
                ImageView imageview2 = (ImageView) view.findViewById(R.id.type15_imageview2);
                ImageView imageview3 = (ImageView) view.findViewById(R.id.type15_imageview3);
                ImageView imageview4 = (ImageView) view.findViewById(R.id.type15_imageview4);
                FashionCommunityBean.DataEntity.ConfigEntity configEntity = config.get(i);
                text_right.setText(configEntity.getCollocation_count() + "人参与");
                text_left.setText("#" + configEntity.getName());
                List<FashionCommunityBean.DataEntity.ConfigEntity.CollocationListEntity> collacationlist = configEntity.getCollocation_list();
                ImageLoader.getInstance().displayImage(collacationlist.get(0).getImg(),imageview1, options);
                ImageLoader.getInstance().displayImage(collacationlist.get(1).getImg(), imageview2, options);
                ImageLoader.getInstance().displayImage(collacationlist.get(2).getImg(), imageview3, options);
                ImageLoader.getInstance().displayImage(collacationlist.get(3).getImg(), imageview4, options);
                map15.put(i, view);
                    if (map15 != null) {
                        if (isFirst15) {
                            type15.linear.addView(map15.get(i));
                        }
                    }

            }

        }
        isFirst15 = false;
    }

    private void initViewHolderUI99999(FashionCommunityBean.DataEntity dataEntity, HolderRemenType99999 holderParent, List<FashionCommunityBean.DataEntity.ConfigEntity> config) {
        if (config != null) {
            HolderRemenType99999 type9 = holderParent;
            ViewGroup.LayoutParams params = type9.convertView.getLayoutParams();
            FashionCommunityBean.DataEntity.ConfigEntity configEntity = dataEntity.getConfig().get(0);
            if (configEntity != null) {
                params.width = Integer.parseInt(configEntity.getImg_width());
                params.height = Integer.parseInt(configEntity.getImg_height());
                type9.convertView.setLayoutParams(params);
            }
        }
    }


    private void initViewHolderUI14(HolderRemenType14 holderParent, DisplayImageOptions options, List<FashionCommunityBean.DataEntity.ConfigEntity> config) {
        HolderRemenType14 type14 = holderParent;
        type14.text_renmen.setText("时尚达人");

        DisplayImageOptions options_circle = ImageLoaderUtils.getCircleDisplayImageOptions();
        int[] smallIcon = {R.mipmap.ico_no1,R.mipmap.ico_no2,R.mipmap.ico_no3};
        if (config != null) {
            for (int i = 0; i < config.size(); i++) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_type14_data_layout,null);
                ImageView imageview1 = (ImageView) view.findViewById(R.id.type14_imageview1);
                ImageView imageView2 = ((ImageView) view.findViewById(R.id.type14_imageview2));
                ImageView imageView3 = (ImageView) view.findViewById(R.id.type14_imageview3);
                ImageView imageView_head = (ImageView)view.findViewById(R.id.type14_head);
                ImageView imageView_small = (ImageView) view.findViewById(R.id.type14_small_imageview);
                TextView text_nickedname = (TextView) view.findViewById(R.id.type14_nicked_name);
                TextView text_colloaction = (TextView) view.findViewById(R.id.type14_collacation_count);
                TextView text_conner = (TextView) view.findViewById(R.id.type14_conner_count);
                FashionCommunityBean.DataEntity.ConfigEntity configEntity = config.get(i);
                text_nickedname.setText(configEntity.getNick_name());
                text_colloaction.setText("作品 " + configEntity.getCollocationCount());
                text_conner.setText("粉丝" + configEntity.getConcernCount());
                imageView_small.setBackgroundResource(smallIcon[i]);
                ImageLoader.getInstance().displayImage(configEntity.getHead_img(),imageView_head,options_circle);
                List<FashionCommunityBean.DataEntity.ConfigEntity.CollocationListEntity> collacationlist = configEntity.getCollocation_list();
                ImageLoader.getInstance().displayImage(collacationlist.get(0).getImg(),imageview1,options);
                ImageLoader.getInstance().displayImage(collacationlist.get(1).getImg(),imageView2,options);
                ImageLoader.getInstance().displayImage(collacationlist.get(2).getImg(), imageView3,options);
                map14.put(i, view);
                if (map14 != null) {
                    if (isFirst14){

                        type14.linear.addView(map14.get(i));
                    }
                }
            }


        }

        isFirst14 = false;

    }


    class HolderRemenType9 extends ViewHolderParent {

        public HolderRemenType9(View convertView) {
            super(convertView);
        }
    }

    class HolderRemenType15 extends ViewHolderParent {
        @Bind(R.id.remen_container)
        LinearLayout linear;
        @Bind(R.id.remenhuati)
        RelativeLayout remenhuati;
        public HolderRemenType15(View convertView) {
            super(convertView);
        }
    }

    class HolderRemenType99999 extends ViewHolderParent {

        public HolderRemenType99999(View convertView) {
            super(convertView);
        }
    }

    class HolderRemenType14 extends ViewHolderParent {
        @Bind(R.id.remen_container)
        LinearLayout linear;
        @Bind(R.id.text_remen)
        TextView text_renmen;
        public HolderRemenType14(View convertView) {
            super(convertView);

        }
    }

    public void addData(List<FashionCommunityBean.DataEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
