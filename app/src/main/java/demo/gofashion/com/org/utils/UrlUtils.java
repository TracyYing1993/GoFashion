package demo.gofashion.com.org.utils;

/**
 * Created by Administrator on 2016/3/24.
 * <p/>
 * 网络接口Url工具类
 */
public class UrlUtils {

    public static final String INDEX_PATH = "http://api.funwear.com/mbfun_server/index.php?m=Home&a=getAppLayoutInfo&id=1&cid=null";

    public static final String INDEX_PAGER_PATH = "http://api.funwear.com/mbfun_server/index.php?m=Product&a=getRecommedProductList&token=&page=%d&num=10";

    //  详情
    public static final String INDEX_DETAIL_PATH = " http://api.funwear.com/mbfun_server/index.php?m=Product&a=ProductClsFilter&token=&IDS=%d";

    //尺码
    public static final String INDEX_SIZE_PATH=" http://api.funwear.com/mbfun_server/index.php?m=Product&a=ProductSizeTableFilter&proD_CLS_ID=%d";

    //评论
    public static final String INDEX_COMMEND_PATH="http://api.funwear.com/mbfun_server/index.php?m=Comment&a=getCommentList&tid=%d&type=2&page=%p";

    //分类
    public static String classify = "http://api.funwear.com/mbfun_server/index.php?m=Home&a=getAppLayoutInfo&id=2&cid=null";
    public static String women_dress = "http://api.funwear.com/mbfun_server/index.php?m=Home&a=getAppLayoutInfo&id=2&cid=4";
    //男装
    public static String men_clothing = "http://api.funwear.com/mbfun_server/index.php?m=Home&a=getAppLayoutInfo&id=2&cid=3";
    //鞋履
    public static String shoes = "http://api.funwear.com/mbfun_server/index.php?m=Home&a=getAppLayoutInfo&id=2&cid=9";
    //箱包
    public static String bag = "http://api.funwear.com/mbfun_server/index.php?m=Home&a=getAppLayoutInfo&id=2&cid=8";
    //配饰
    public static String accessories = "http://api.funwear.com/mbfun_server/index.php?m=Home&a=getAppLayoutInfo&id=2&cid=5";
    //美妆
    public static String Beauty = "http://api.funwear.com/mbfun_server/index.php?m=Home&a=getAppLayoutInfo&id=2&cid=1";
    //生活
    public static String live = "http://api.funwear.com/mbfun_server/index.php?m=Home&a=getAppLayoutInfo&id=2&cid=6";
    public static String MAN_AND_WOMAN_URL = "http://api.funwear.com/mbfun_server/index.php?m=Collocation&a=getCollocationListByUserType&token=&userId=&userType=%u&page=%p";


    //上新
    public static String TopNew = "http://api.funwear.com/mbfun_server/index.php?m=Product&a=ProductClsCommonSearchFilter&pageIndex=1&CategoryId=&sortInfo={%22desc%22%3A-1%2C%22sortField%22%3A3}&keyWord=&brandCode=%d&pageSize=20";
    //热销
    public static String Hotsell = "http://api.funwear.com/mbfun_server/index.php?m=Product&a=ProductClsCommonSearchFilter&pageIndex=0&CategoryId=&sortInfo=1&keyWord=&brandCode=%d&pageSize=20";
    //
    public static String HotDetail ="http://api.funwear.com/mbfun_server/index.php?m=Product&a=getProductCategoryList&cid=%d";
    //上新2
    public static String TopNew2 = "http://api.funwear.com/mbfun_server/index.php?m=Product&a=ProductClsCommonSearchFilter&pageIndex=1&CategoryId=%d&sortInfo=&keyWord=&pageSize=20";
    //热销2
    public static String Hotsell2 = "http://api.funwear.com/mbfun_server/index.php?m=Product&a=ProductClsCommonSearchFilter&pageIndex=1&CategoryId=%d&sortInfo=1&keyWord=&pageSize=20";

    //社区之热门
    public static String HOT = "http://api.funwear.com/mbfun_server/index.php?m=Home&a=getHotLayoutInfo";
}
