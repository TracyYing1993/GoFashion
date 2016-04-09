package demo.gofashion.com.org.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import demo.gofashion.com.org.bean.GridViewDetailBean;
import demo.gofashion.com.org.utils.UrlUtils;
import demo.gofashion.com.org.utils.db.CollectSQLiteDBUtils;
import demo.gofashion.com.org.utils.netutils.ImageLoaderUtils;
import demo.gofashion.com.org.utils.netutils.OkHttpUtils;
import demo.gofashion.com.org.widget.ViewPagerGroup;

/**
 * Created by Administrator on 2016/3/25.
 */
public class GridViewDetailActivity extends AppCompatActivity implements OkHttpUtils.callBack {
    private static final String TAG = GridViewDetailActivity.class.getSimpleName();
    private String url;
    private Gson gson;
    private GridViewDetailBean gridViewDetailBean;

    private boolean isClickChanged = true;

    //商品图片
    @Bind(R.id.viewpagerGroup)
    ViewPagerGroup viewpagerGroup;

    //标题
    @Bind(R.id.text_title_detail)
    TextView text_title_detail;
    //售卖价钱
    @Bind(R.id.text_sale_price_detail)
    TextView text_sale_price_detail;
    //原始价钱
    @Bind(R.id.text_price_detail)
    TextView text_price_detail;
    //月销量
    @Bind(R.id.text_month_sales_detail)
    TextView text_month_sales_detail;
    //好评度
    @Bind(R.id.text_haoping_detail)
    TextView text_haoping_detail;
    //收藏
    @Bind(R.id.text_collect_detail)
    TextView text_collect_detail;
    //包邮
    @Bind(R.id.text_you_detail)
    TextView text_you_detail;
    //评价图片
    @Bind(R.id.rating_image_container)
    LinearLayout rating_image_container;
    //编辑推荐
    @Bind(R.id.text_description_detail)
    TextView text_description_detail;
    //设置品牌
    @Bind(R.id.text_brand_detail)
    TextView text_brand_detail;
    //设置款名
    @Bind(R.id.text_product_name_detail)
    TextView text_product_name_detail;
    //设置款号
    @Bind(R.id.text_product_num_detail)
    TextView text_product_num_detail;

    //商品展示
    @Bind(R.id.product_image_container)
    LinearLayout product_image_container;

    //折扣
    @Bind(R.id.text_price_rebate_detail)
    TextView text_price_rebate_detail;

    //购物袋中的详情
    @Bind(R.id.shopping_layout_container)
    RelativeLayout shopping_layout_container;

    //展示mainImage
    @Bind(R.id.image_mainImage_detail)
    ImageView image_mainImage_detail;

    //购物销售价钱
    @Bind(R.id.text_price_shopping)
    TextView text_price_shopping;

    //购物原始价钱
    @Bind(R.id.text_sale_price_shopping)
    TextView text_sale_price_shopping;

    //商品编号
    @Bind(R.id.text_product_num_shopping)
    TextView text_product_num_shopping;

    //库存
    @Bind(R.id.text_library_num_shopping)
    TextView text_library_num_shopping;

    //颜色
    @Bind(R.id.text_product_color_detail)
    TextView text_product_color_detail;

    //要选择的产品小图标颜色
    @Bind(R.id.product_smallImageContainer_detail)
    LinearLayout product_smallImageContainer_detail;

    //尺码布局
    @Bind(R.id.size_container_layout)
    LinearLayout size_container_layout;

    //选择产品数量
    @Bind(R.id.text_num_detail)
    TextView text_num_detail;

    @Bind(R.id.text_size_detail)
    TextView text_size_detail;

    //评价
    @Bind(R.id.text_rating_detail)
    TextView text_rating_detail;

    //评价容器
    @Bind(R.id.comment_container_layut)
    RelativeLayout comment_container_layut;

    //尺码参数
    @Bind(R.id.image_size_detail)
    RelativeLayout image_size_detail;

    //收藏
    @Bind(R.id.btn_collect_detail)
    Button btn_collect_detail;


    private DisplayImageOptions options;
    private int num;

    private CollectSQLiteDBUtils db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_detail_layout);
        ButterKnife.bind(this);
        db = new CollectSQLiteDBUtils(this);

        gson = new Gson();

        //构建显示图片的属性
        options = ImageLoaderUtils.getDisplayImageOptions();

        url = getIntent().getStringExtra("url");

        if (!TextUtils.isEmpty(url)) {
            //连网下载
            OkHttpUtils.callBackUIDataFormatOne(url, OkHttpUtils.TYPE_TEXT, this);
        }
    }

    @Override
    public void callBackUIString(String data) {
        if (!TextUtils.isEmpty(data)) {
            gridViewDetailBean = gson.fromJson(data, GridViewDetailBean.class);
            List<GridViewDetailBean.ResultsEntity> results = gridViewDetailBean.getResults();
            if (results != null) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    List<GridViewDetailBean.ResultsEntity.ProPicUrlEntity> proPicUrl = results.get(i).getProPicUrl();
                    for (int j = 0; j < proPicUrl.size(); j++) {
                        String filE_path = proPicUrl.get(j).getFilE_PATH();
                        list.add(filE_path);
                    }
                }
                Log.e(TAG, "callBackUIString: file_pagths=" + list.size());
                viewpagerGroup.setImageUrl(list);
            }
            if (gridViewDetailBean.getResults() != null) {
                initViewsData(gridViewDetailBean.getResults());
            }
        }
    }

    private void initViewsData(List<GridViewDetailBean.ResultsEntity> results) {
        GridViewDetailBean.ResultsEntity resultsEntity = results.get(0);
        final GridViewDetailBean.ResultsEntity.ClsInfoEntity clsInfo = resultsEntity.getClsInfo();
        final GridViewDetailBean.ResultsEntity.CommonCountTotalEntity commonCountTotal = resultsEntity.getCommonCountTotal();
        //设置评价图片
        setCommendImg(commonCountTotal);
        //评价容器参数
        setCommendContainerValues(clsInfo, commonCountTotal);
        //设置每个控件的内容信息
        setViewsContentUi(resultsEntity, clsInfo, commonCountTotal);
        //连网加载显示图片
        setDisplayMainImg(clsInfo);

        final List<GridViewDetailBean.ResultsEntity.ColorListEntity> colorList = resultsEntity.getColorList();
        if (colorList != null) {
            //要选择的产品小图标颜色 image_product_small_detail;连网加载显示
            setProductSmallImg(colorList);
        }
        //尺码布局  LinearLayout size_container_layout;
        setSizeLayoutContent(resultsEntity);

        //设置LinearLyaout中的商品图片
        setLinearLayouContentImg(resultsEntity);
    }

    //设置评价图片
    private void setCommendImg(GridViewDetailBean.ResultsEntity.CommonCountTotalEntity commonCountTotal) {
        double avgCommentNew = Double.parseDouble(commonCountTotal.getAvgCommentNew());
        for (int i = 1; i <= 5; i++) {
            ImageView img = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
            params.leftMargin = 8;
            img.setLayoutParams(params);
            if (i <= avgCommentNew) {
                img.setImageResource(R.drawable.add1);
            } else {
                img.setImageResource(R.drawable.add2);
            }
            rating_image_container.addView(img);
        }
    }

    //评价容器参数
    private void setCommendContainerValues(final GridViewDetailBean.ResultsEntity.ClsInfoEntity clsInfo, final GridViewDetailBean.ResultsEntity.CommonCountTotalEntity commonCountTotal) {
        comment_container_layut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GridViewDetailActivity.this, CommendActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", OkHttpUtils.getReplaceFormat(UrlUtils.INDEX_COMMEND_PATH, "%d", clsInfo.getCode()));
                bundle.putDouble("commendNum", Double.parseDouble(commonCountTotal.getAvgCommentNew()));
                bundle.putInt("commendCount", commonCountTotal.getCommentCount());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //设置每个控件的内容信息
    private void setViewsContentUi(GridViewDetailBean.ResultsEntity resultsEntity, final GridViewDetailBean.ResultsEntity.ClsInfoEntity clsInfo, GridViewDetailBean.ResultsEntity.CommonCountTotalEntity commonCountTotal) {
        //设置 title
        text_title_detail.setText(clsInfo.getName());
        //设置原始价钱
        double price = Double.parseDouble(clsInfo.getPrice());
        text_price_detail.setText(ImageLoaderUtils.getFormatPrice(price));
        //购物原始价钱 text_price_shopping;
        text_price_shopping.setText(text_price_detail.getText());

        //设置售卖价钱
        double price1 = Double.parseDouble(clsInfo.getSale_price());
        text_sale_price_detail.setText(ImageLoaderUtils.getFormatPrice(price1));
        //购物销售价钱 text_sale_price_shopping;
        text_sale_price_shopping.setText(text_sale_price_detail.getText());

        if (price != price1) {
            double rebate = price1 / price;
            DecimalFormat format = new DecimalFormat("0.0");
            String formatPrice = format.format(rebate);
            double priceRebate = Double.parseDouble(formatPrice) * 10;
            text_price_rebate_detail.setText(priceRebate + "折");
            text_price_rebate_detail.setVisibility(View.VISIBLE);
        } else {
            text_price_rebate_detail.setVisibility(View.GONE);
        }

        //尺寸参数
        image_size_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GridViewDetailActivity.this, SizeActivity.class).putExtra("codeId", clsInfo.getCode()));
            }
        });

        //设置月销量
        text_month_sales_detail.setText(text_month_sales_detail.getText() + " " + commonCountTotal.getSaleQty() + "件");
        //设置好评度
        text_haoping_detail.setText(text_haoping_detail.getText() + " " + commonCountTotal.getPercent());
        //设置收藏
        text_collect_detail.setText(text_collect_detail.getText() + " " + commonCountTotal.getFavoritCount());
        //设置评价
        text_rating_detail.setText("评论(" + commonCountTotal.getCommentCount() + ")");
        //设置包邮
        List<GridViewDetailBean.ResultsEntity.ActivityEntity> activity = resultsEntity.getActivity();
        GridViewDetailBean.ResultsEntity.ActivityEntity activityEntity = activity.get(0);
        text_you_detail.setText(activityEntity.getName());


        //设置编辑推荐
        text_description_detail.setText(clsInfo.getDescription());
        //设置品牌
        text_brand_detail.setText(text_brand_detail.getText() + " " + clsInfo.getBrand());
        //设置款名
        text_product_name_detail.setText(text_product_name_detail.getText() + " " + clsInfo.getName());
        //设置款号
        text_product_num_detail.setText(text_product_num_detail.getText() + " " + clsInfo.getCode());
        //商品编号text_product_num_shopping
        text_product_num_shopping.setText(text_product_num_shopping.getText() + " " + clsInfo.getCode());
        //库存text_library_num_shopping
        text_library_num_shopping.setText("库存" + clsInfo.getStockCount() + "件");

        num = clsInfo.getStockCount();

    }

    //连网加载显示图片
    private void setDisplayMainImg(final GridViewDetailBean.ResultsEntity.ClsInfoEntity clsInfo) {
        final String mainImage = clsInfo.getMainImage();
        if (!TextUtils.isEmpty(mainImage)) {
            DisplayImageOptions imageOptions = ImageLoaderUtils.getDisplayImageOptionsRoundeSmall();
            ImageLoader.getInstance().displayImage(mainImage, image_mainImage_detail, imageOptions);
            //收藏
            btn_collect_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    OkHttpUtils.callBackUIDataFormatOne(mainImage, OkHttpUtils.TYPE_BYTE, new OkHttpUtils.callBack() {
                        @Override
                        public void callBackUIString(String data) {

                        }

                        @Override
                        public void callBackUIByte(byte[] datas) {
                            if (datas != null) {
                                //价钱
                                //id
                                String code = clsInfo.getCode();
                                //款名
                                String product_name = clsInfo.getName();
                                String sale_price = clsInfo.getSale_price();
                                long row = db.addAllCloumnData(code, product_name, datas, url, sale_price);
                                if (row > 0) {
                                    Toast.makeText(GridViewDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(GridViewDetailActivity.this, "收藏失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    });
                }
            });
        } else {
            Log.e(TAG, "initViewsData: 显示图片的路径不对 image_mainImage_detail");
        }
    }

    //要选择的产品小图标颜色 image_product_small_detail;连网加载显示
    private void setProductSmallImg(final List<GridViewDetailBean.ResultsEntity.ColorListEntity> colorList) {
        for (int i = 0; i < colorList.size(); i++) {
            String picurl = colorList.get(i).getPicurl();
            if (!TextUtils.isEmpty(picurl)) {
                ImageView img = new ImageView(this);
                img.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60, 50);
                params.leftMargin = 15;
                params.topMargin = 10;
                img.setLayoutParams(params);
                img.setTag(i);
                ImageLoader.getInstance().displayImage(picurl, img, options);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        text_product_color_detail.setText("");
                        int index = (Integer) v.getTag();
                        //颜色 text_product_color_detail;
                        text_product_color_detail.setText("颜色:" + colorList.get(index).getName());

                    }
                });

                product_smallImageContainer_detail.addView(img);

            } else {
                Log.e(TAG, "initViewsData: 颜色小图标路径不对");
            }

        }
        Log.e(TAG, "initViewsData: getChildCount=" + product_smallImageContainer_detail.getChildCount());
    }

    //尺码布局  LinearLayout size_container_layout;
    private void setSizeLayoutContent(GridViewDetailBean.ResultsEntity resultsEntity) {
        List<GridViewDetailBean.ResultsEntity.SpecListEntity> specList = resultsEntity.getSpecList();
        if (specList != null) {
            for (int i = 0; i < specList.size(); i++) {
                final TextView tv = new TextView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = 15;
                params.topMargin = 10;
                tv.setLayoutParams(params);
                tv.setPadding(5, 5, 5, 5);
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.border_shape);
                tv.setText(specList.get(i).getName());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        text_size_detail.setText("");
                        text_size_detail.setText("尺码:" + tv.getText());
                    }
                });
                size_container_layout.addView(tv);
            }
        }
    }

    //设置LinearLyaout中的商品图片
    private void setLinearLayouContentImg(GridViewDetailBean.ResultsEntity resultsEntity) {
        List<GridViewDetailBean.ResultsEntity.ClsPicUrlEntity> clsPicUrl = resultsEntity.getClsPicUrl();
        if (clsPicUrl != null) {
            for (int i = 0; i < clsPicUrl.size(); i++) {
                String filE_path = clsPicUrl.get(i).getFilE_PATH();
                if (!TextUtils.isEmpty(filE_path)) {
                    ImageView img = new ImageView(this);
                    img.setScaleType(ImageView.ScaleType.FIT_XY);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    img.setLayoutParams(params);

                    ImageLoader.getInstance().displayImage(filE_path, img, options);
                    product_image_container.addView(img);
                } else {
                    Log.e(TAG, "initViewsData: LinearLyaout中的商品图片路径不对");
                }
            }
            Log.e(TAG, "initViewsData: container:count" + product_image_container.getChildCount());
        } else {
            Log.e(TAG, "initViewsData: 商品展示失败");
        }
    }


    @Override
    public void callBackUIByte(byte[] datas) {

    }

    int index = 1;

    //点击事件
    public void clickButton(View view) {
        switch (view.getId()) {
            case R.id.text_sub_detail:
                //购买数量减少
                subShoppingNum();
                break;
            case R.id.text_add_detail:
                //购买数量增加
                addShopinngNum();
                break;
            case R.id.btn_share_detail:
                //分享
                oneshare();
                break;
            case R.id.btn_shopping_detail:
                //购物袋
                startActivity(new Intent(this,CollectActivity.class));
                break;
            case R.id.image_close_shopping:
                //关闭购物详情
                hideShopping();
                break;
            case R.id.btn_add_shopping_detail:
                //添加到购物袋
                hideShopping();
                break;
            case R.id.btn_submit_Ok_shopping:
                //确定
                break;
        }


    }

    //购买数量减少
    private void subShoppingNum() {
        //选择产品数量 text_num_detail;
        index--;
        if (index > 0) {
            text_num_detail.setText(index + "");
        } else {
            index = 1;
            text_num_detail.setText(index + "");
        }
    }

    //购买数量增加
    private void addShopinngNum() {
        Log.e(TAG, "clickButton: num=" + num + "/index=" + index);
        index++;
        if (index <= 3) {
            text_num_detail.setText(index + "");
            text_num_detail.setEnabled(true);
        } else if (num > 3 || index > num) {
            index = 3;
            text_num_detail.setText(index + "");
            text_num_detail.setEnabled(false);
            Toast.makeText(GridViewDetailActivity.this, "每人仅限购" + index + "件", Toast.LENGTH_SHORT).show();
        }
    }


    //一键分享功能
    public void oneshare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        // oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.baidu.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.baidu.com");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.baidu.com");

        // 启动分享GUI
        oks.show(this);

    }


    /**
     * 隐藏购物
     */
    private void hideShopping() {
        if (isClickChanged) {
            image_mainImage_detail.setVisibility(View.VISIBLE);
            shopping_layout_container.setVisibility(View.VISIBLE);
            isClickChanged = !isClickChanged;
        } else {
            image_mainImage_detail.setVisibility(View.GONE);
            shopping_layout_container.setVisibility(View.GONE);
            isClickChanged = !isClickChanged;
        }
    }

}
