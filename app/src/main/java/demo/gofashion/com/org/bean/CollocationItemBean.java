package demo.gofashion.com.org.bean;

import java.util.List;

/**
 * Created by 杨裕森 on 2016-03-25.
 */
public class CollocationItemBean {


    /**
     * status : 1
     * data : {"comment_count":"9","like_user_list":[{"nick_name":"佼佼虢的店","head_img":"http://metersbonwe.qiniucdn.com/b3a0457cdda84859872575ff60a1d2e4weAtGA7r.png","user_id":"121fbc2f-abff-4257-9f0e-753aca8da080","head_v_type":"0"},{"nick_name":"演绎","head_img":"http://metersbonwe.qiniucdn.com/defmale.jpg","user_id":"696bd7f9-04ed-46ef-8b5e-ab2cf019ae86","head_v_type":"0"},{"nick_name":"jigl","head_img":"http://metersbonwe.qiniucdn.com/4a53cf41bc4340328fa0eb8760f1f8e4we5wet19.png","user_id":"70f6eeba-4c0d-48af-9b45-880cc44677c0","head_v_type":"0"},{"nick_name":"Carol","head_img":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLBX1wOFnGm2IL9GPjyPsQQHCoMicHU7vlg2Cf73oBdbB3bSaSvC3o2H2bkFLSaRncibBhyEWEVwSAGA/0","user_id":"868d63fe-c802-4d97-9952-8348e3a76ab3","head_v_type":"0"},{"nick_name":"小小tina","head_img":"http://metersbonwe.qiniucdn.com/defmale.jpg","user_id":"9ade3d73-604d-4bf0-835c-a7ee91194269","head_v_type":"0"},{"nick_name":"Sara的美衣百宝箱","head_img":"http://metersbonwe.qiniucdn.com/a63064acf8b4403480bb41e9a183a67awepzUdk1.png","user_id":"8ece7ccc-ce26-454a-bbd8-8e20dd0e2d78","head_v_type":"0"},{"nick_name":"Raokaiqiong","head_img":"http://metersbonwe.qiniucdn.com/defmale.jpg","user_id":"d804ad32-9172-469b-80fc-c76cd8bf5fac","head_v_type":"0"},{"nick_name":"杨晓燕","head_img":"http://metersbonwe.qiniucdn.com/0ffe2633f4394283aeed2386e77e6c8aweftJPpG.png","user_id":"feb98777-9e53-4a73-accd-627f43f4d8e5","head_v_type":"0"},{"nick_name":"风格wd","head_img":"http://metersbonwe.qiniucdn.com/defmale.jpg","user_id":"e0c58eca-f230-4664-bb24-2c3dc0ecb23d","head_v_type":"0"},{"nick_name":"Brave","head_img":"http://wx.qlogo.cn/mmopen/H2IH3iaxzMTMjVLibZA9F62doAXuVmvQBlNNWzN3uCBZ11No1OyicOCrRy2iaW4kXqW8kgUtzT8bZ6CDmMn1tpShBdLKZNHJvyB7/0","user_id":"8627c211-402f-4dc9-8f27-108ec1ab5b4b","head_v_type":"0"}],"stick_img_url":"","designer":{"nick_name":"海韵的眼泪","isConcerned":false,"head_img":"http://metersbonwe.qiniucdn.com/10166.jpg","user_id":"1a9c453b-0520-46fa-8f36-2211028cae09","concernedCount":"979","userSignature":"","head_v_type":"0"},"isFind":1,"img":"http://7xjir4.com2.z0.glb.qiniucdn.com/FplqNJu6Pk79HOIo4_uah8kRFRFi","img_height":"1440","content_info":"","item_str":"_234902_","id":"382039","product_list":[{"color_value":"其它","product_img":"http://img7.ibanggo.com/sources/images/goods/MB/234902/234902_00.jpg","cate_id":"172","stock_num":0,"is_delete":"0","cate_value":"女装/夹克","brand_code":"MB","product":{"status":0,"product_name":"女局部拼接牛仔茄克","brand_id":"1","stock_num":0,"product_url":"http://img7.ibanggo.com/sources/images/goods/MB/234902/234902_00.jpg","brandName":"Metersbonwe","price":"150.000000","brandUrl":"http://img5.ibanggo.com/sources/cms/brand_logo/metersbonwe1.png","market_price":"299.000000","product_sys_code":"234902","on_sale_date":"2015-11-13 15:25:02","spec_price_list":["150.000000"],"activity_icon":"","prodClsTag":[{"tagType":0,"remark":"","tagName":"折扣","tagUrl":"http://metersbonwe.qiniucdn.com/icon_sale2.png"}],"activity_rule":""},"id":"100089","price":"150.000000","market_price":"299.000000","product_code":"234902","color_code":"","is_repair":"0","create_time":"2015-11-16 11:27:03","user_id":"1a9c453b-0520-46fa-8f36-2211028cae09","brand_value":"Metersbonwe"}],"tab_str":"靴子","is_love":0,"comment_score":"45","brand_str":"_MB_","tag_list":"[{\"attributes\":{\"code\":\"234902\",\"id\":\"100089\",\"type\":100,\"flip\":0},\"brandCode\":\"MB\",\"y\":0.3668085,\"text\":\"Metersbonwe\",\"x\":0.45970488,\"flip\":0}]","video_url":"","like_count":"154","create_time":"2016-03-25 08:28:08","user_id":"1a9c453b-0520-46fa-8f36-2211028cae09","user_json":"{\"type\":1,\"info\":\"女\"}","img_width":"1080"}
     * info :
     */
    private int status;
    private DataEntity data;
    private String info;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public DataEntity getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public class DataEntity {
        /**
         * comment_count : 9
         * like_user_list : [{"nick_name":"佼佼虢的店","head_img":"http://metersbonwe.qiniucdn.com/b3a0457cdda84859872575ff60a1d2e4weAtGA7r.png","user_id":"121fbc2f-abff-4257-9f0e-753aca8da080","head_v_type":"0"},{"nick_name":"演绎","head_img":"http://metersbonwe.qiniucdn.com/defmale.jpg","user_id":"696bd7f9-04ed-46ef-8b5e-ab2cf019ae86","head_v_type":"0"},{"nick_name":"jigl","head_img":"http://metersbonwe.qiniucdn.com/4a53cf41bc4340328fa0eb8760f1f8e4we5wet19.png","user_id":"70f6eeba-4c0d-48af-9b45-880cc44677c0","head_v_type":"0"},{"nick_name":"Carol","head_img":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLBX1wOFnGm2IL9GPjyPsQQHCoMicHU7vlg2Cf73oBdbB3bSaSvC3o2H2bkFLSaRncibBhyEWEVwSAGA/0","user_id":"868d63fe-c802-4d97-9952-8348e3a76ab3","head_v_type":"0"},{"nick_name":"小小tina","head_img":"http://metersbonwe.qiniucdn.com/defmale.jpg","user_id":"9ade3d73-604d-4bf0-835c-a7ee91194269","head_v_type":"0"},{"nick_name":"Sara的美衣百宝箱","head_img":"http://metersbonwe.qiniucdn.com/a63064acf8b4403480bb41e9a183a67awepzUdk1.png","user_id":"8ece7ccc-ce26-454a-bbd8-8e20dd0e2d78","head_v_type":"0"},{"nick_name":"Raokaiqiong","head_img":"http://metersbonwe.qiniucdn.com/defmale.jpg","user_id":"d804ad32-9172-469b-80fc-c76cd8bf5fac","head_v_type":"0"},{"nick_name":"杨晓燕","head_img":"http://metersbonwe.qiniucdn.com/0ffe2633f4394283aeed2386e77e6c8aweftJPpG.png","user_id":"feb98777-9e53-4a73-accd-627f43f4d8e5","head_v_type":"0"},{"nick_name":"风格wd","head_img":"http://metersbonwe.qiniucdn.com/defmale.jpg","user_id":"e0c58eca-f230-4664-bb24-2c3dc0ecb23d","head_v_type":"0"},{"nick_name":"Brave","head_img":"http://wx.qlogo.cn/mmopen/H2IH3iaxzMTMjVLibZA9F62doAXuVmvQBlNNWzN3uCBZ11No1OyicOCrRy2iaW4kXqW8kgUtzT8bZ6CDmMn1tpShBdLKZNHJvyB7/0","user_id":"8627c211-402f-4dc9-8f27-108ec1ab5b4b","head_v_type":"0"}]
         * stick_img_url :
         * designer : {"nick_name":"海韵的眼泪","isConcerned":false,"head_img":"http://metersbonwe.qiniucdn.com/10166.jpg","user_id":"1a9c453b-0520-46fa-8f36-2211028cae09","concernedCount":"979","userSignature":"","head_v_type":"0"}
         * isFind : 1
         * img : http://7xjir4.com2.z0.glb.qiniucdn.com/FplqNJu6Pk79HOIo4_uah8kRFRFi
         * img_height : 1440
         * content_info :
         * item_str : _234902_
         * id : 382039
         * product_list : [{"color_value":"其它","product_img":"http://img7.ibanggo.com/sources/images/goods/MB/234902/234902_00.jpg","cate_id":"172","stock_num":0,"is_delete":"0","cate_value":"女装/夹克","brand_code":"MB","product":{"status":0,"product_name":"女局部拼接牛仔茄克","brand_id":"1","stock_num":0,"product_url":"http://img7.ibanggo.com/sources/images/goods/MB/234902/234902_00.jpg","brandName":"Metersbonwe","price":"150.000000","brandUrl":"http://img5.ibanggo.com/sources/cms/brand_logo/metersbonwe1.png","market_price":"299.000000","product_sys_code":"234902","on_sale_date":"2015-11-13 15:25:02","spec_price_list":["150.000000"],"activity_icon":"","prodClsTag":[{"tagType":0,"remark":"","tagName":"折扣","tagUrl":"http://metersbonwe.qiniucdn.com/icon_sale2.png"}],"activity_rule":""},"id":"100089","price":"150.000000","market_price":"299.000000","product_code":"234902","color_code":"","is_repair":"0","create_time":"2015-11-16 11:27:03","user_id":"1a9c453b-0520-46fa-8f36-2211028cae09","brand_value":"Metersbonwe"}]
         * tab_str : 靴子
         * is_love : 0
         * comment_score : 45
         * brand_str : _MB_
         * tag_list : [{"attributes":{"code":"234902","id":"100089","type":100,"flip":0},"brandCode":"MB","y":0.3668085,"text":"Metersbonwe","x":0.45970488,"flip":0}]
         * video_url :
         * like_count : 154
         * create_time : 2016-03-25 08:28:08
         * user_id : 1a9c453b-0520-46fa-8f36-2211028cae09
         * user_json : {"type":1,"info":"女"}
         * img_width : 1080
         */
        private String comment_count;
        private List<Like_user_listEntity> like_user_list;
        private String stick_img_url;
        private DesignerEntity designer;
        private int isFind;
        private String img;
        private String img_height;
        private String content_info;
        private String item_str;
        private String id;
        private List<Product_listEntity> product_list;
        private String tab_str;
        private int is_love;
        private String comment_score;
        private String brand_str;
        private String tag_list;
        private String video_url;
        private String like_count;
        private String create_time;
        private String user_id;
        private String user_json;
        private String img_width;

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public void setLike_user_list(List<Like_user_listEntity> like_user_list) {
            this.like_user_list = like_user_list;
        }

        public void setStick_img_url(String stick_img_url) {
            this.stick_img_url = stick_img_url;
        }

        public void setDesigner(DesignerEntity designer) {
            this.designer = designer;
        }

        public void setIsFind(int isFind) {
            this.isFind = isFind;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setImg_height(String img_height) {
            this.img_height = img_height;
        }

        public void setContent_info(String content_info) {
            this.content_info = content_info;
        }

        public void setItem_str(String item_str) {
            this.item_str = item_str;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setProduct_list(List<Product_listEntity> product_list) {
            this.product_list = product_list;
        }

        public void setTab_str(String tab_str) {
            this.tab_str = tab_str;
        }

        public void setIs_love(int is_love) {
            this.is_love = is_love;
        }

        public void setComment_score(String comment_score) {
            this.comment_score = comment_score;
        }

        public void setBrand_str(String brand_str) {
            this.brand_str = brand_str;
        }

        public void setTag_list(String tag_list) {
            this.tag_list = tag_list;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public void setLike_count(String like_count) {
            this.like_count = like_count;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setUser_json(String user_json) {
            this.user_json = user_json;
        }

        public void setImg_width(String img_width) {
            this.img_width = img_width;
        }

        public String getComment_count() {
            return comment_count;
        }

        public List<Like_user_listEntity> getLike_user_list() {
            return like_user_list;
        }

        public String getStick_img_url() {
            return stick_img_url;
        }

        public DesignerEntity getDesigner() {
            return designer;
        }

        public int getIsFind() {
            return isFind;
        }

        public String getImg() {
            return img;
        }

        public String getImg_height() {
            return img_height;
        }

        public String getContent_info() {
            return content_info;
        }

        public String getItem_str() {
            return item_str;
        }

        public String getId() {
            return id;
        }

        public List<Product_listEntity> getProduct_list() {
            return product_list;
        }

        public String getTab_str() {
            return tab_str;
        }

        public int getIs_love() {
            return is_love;
        }

        public String getComment_score() {
            return comment_score;
        }

        public String getBrand_str() {
            return brand_str;
        }

        public String getTag_list() {
            return tag_list;
        }

        public String getVideo_url() {
            return video_url;
        }

        public String getLike_count() {
            return like_count;
        }

        public String getCreate_time() {
            return create_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getUser_json() {
            return user_json;
        }

        public String getImg_width() {
            return img_width;
        }

        public class Like_user_listEntity {
            /**
             * nick_name : 佼佼虢的店
             * head_img : http://metersbonwe.qiniucdn.com/b3a0457cdda84859872575ff60a1d2e4weAtGA7r.png
             * user_id : 121fbc2f-abff-4257-9f0e-753aca8da080
             * head_v_type : 0
             */
            private String nick_name;
            private String head_img;
            private String user_id;
            private String head_v_type;

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setHead_v_type(String head_v_type) {
                this.head_v_type = head_v_type;
            }

            public String getNick_name() {
                return nick_name;
            }

            public String getHead_img() {
                return head_img;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getHead_v_type() {
                return head_v_type;
            }
        }

        public class DesignerEntity {
            /**
             * nick_name : 海韵的眼泪
             * isConcerned : false
             * head_img : http://metersbonwe.qiniucdn.com/10166.jpg
             * user_id : 1a9c453b-0520-46fa-8f36-2211028cae09
             * concernedCount : 979
             * userSignature :
             * head_v_type : 0
             */
            private String nick_name;
            private boolean isConcerned;
            private String head_img;
            private String user_id;
            private String concernedCount;
            private String userSignature;
            private String head_v_type;

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public void setIsConcerned(boolean isConcerned) {
                this.isConcerned = isConcerned;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setConcernedCount(String concernedCount) {
                this.concernedCount = concernedCount;
            }

            public void setUserSignature(String userSignature) {
                this.userSignature = userSignature;
            }

            public void setHead_v_type(String head_v_type) {
                this.head_v_type = head_v_type;
            }

            public String getNick_name() {
                return nick_name;
            }

            public boolean isIsConcerned() {
                return isConcerned;
            }

            public String getHead_img() {
                return head_img;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getConcernedCount() {
                return concernedCount;
            }

            public String getUserSignature() {
                return userSignature;
            }

            public String getHead_v_type() {
                return head_v_type;
            }
        }

        public class Product_listEntity {
            /**
             * color_value : 其它
             * product_img : http://img7.ibanggo.com/sources/images/goods/MB/234902/234902_00.jpg
             * cate_id : 172
             * stock_num : 0
             * is_delete : 0
             * cate_value : 女装/夹克
             * brand_code : MB
             * product : {"status":0,"product_name":"女局部拼接牛仔茄克","brand_id":"1","stock_num":0,"product_url":"http://img7.ibanggo.com/sources/images/goods/MB/234902/234902_00.jpg","brandName":"Metersbonwe","price":"150.000000","brandUrl":"http://img5.ibanggo.com/sources/cms/brand_logo/metersbonwe1.png","market_price":"299.000000","product_sys_code":"234902","on_sale_date":"2015-11-13 15:25:02","spec_price_list":["150.000000"],"activity_icon":"","prodClsTag":[{"tagType":0,"remark":"","tagName":"折扣","tagUrl":"http://metersbonwe.qiniucdn.com/icon_sale2.png"}],"activity_rule":""}
             * id : 100089
             * price : 150.000000
             * market_price : 299.000000
             * product_code : 234902
             * color_code :
             * is_repair : 0
             * create_time : 2015-11-16 11:27:03
             * user_id : 1a9c453b-0520-46fa-8f36-2211028cae09
             * brand_value : Metersbonwe
             */
            private String color_value;
            private String product_img;
            private String cate_id;
            private int stock_num;
            private String is_delete;
            private String cate_value;
            private String brand_code;
            private ProductEntity product;
            private String id;
            private String price;
            private String market_price;
            private String product_code;
            private String color_code;
            private String is_repair;
            private String create_time;
            private String user_id;
            private String brand_value;

            public void setColor_value(String color_value) {
                this.color_value = color_value;
            }

            public void setProduct_img(String product_img) {
                this.product_img = product_img;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public void setStock_num(int stock_num) {
                this.stock_num = stock_num;
            }

            public void setIs_delete(String is_delete) {
                this.is_delete = is_delete;
            }

            public void setCate_value(String cate_value) {
                this.cate_value = cate_value;
            }

            public void setBrand_code(String brand_code) {
                this.brand_code = brand_code;
            }

            public void setProduct(ProductEntity product) {
                this.product = product;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public void setProduct_code(String product_code) {
                this.product_code = product_code;
            }

            public void setColor_code(String color_code) {
                this.color_code = color_code;
            }

            public void setIs_repair(String is_repair) {
                this.is_repair = is_repair;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setBrand_value(String brand_value) {
                this.brand_value = brand_value;
            }

            public String getColor_value() {
                return color_value;
            }

            public String getProduct_img() {
                return product_img;
            }

            public String getCate_id() {
                return cate_id;
            }

            public int getStock_num() {
                return stock_num;
            }

            public String getIs_delete() {
                return is_delete;
            }

            public String getCate_value() {
                return cate_value;
            }

            public String getBrand_code() {
                return brand_code;
            }

            public ProductEntity getProduct() {
                return product;
            }

            public String getId() {
                return id;
            }

            public String getPrice() {
                return price;
            }

            public String getMarket_price() {
                return market_price;
            }

            public String getProduct_code() {
                return product_code;
            }

            public String getColor_code() {
                return color_code;
            }

            public String getIs_repair() {
                return is_repair;
            }

            public String getCreate_time() {
                return create_time;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getBrand_value() {
                return brand_value;
            }

            public class ProductEntity {
                /**
                 * status : 0
                 * product_name : 女局部拼接牛仔茄克
                 * brand_id : 1
                 * stock_num : 0
                 * product_url : http://img7.ibanggo.com/sources/images/goods/MB/234902/234902_00.jpg
                 * brandName : Metersbonwe
                 * price : 150.000000
                 * brandUrl : http://img5.ibanggo.com/sources/cms/brand_logo/metersbonwe1.png
                 * market_price : 299.000000
                 * product_sys_code : 234902
                 * on_sale_date : 2015-11-13 15:25:02
                 * spec_price_list : ["150.000000"]
                 * activity_icon :
                 * prodClsTag : [{"tagType":0,"remark":"","tagName":"折扣","tagUrl":"http://metersbonwe.qiniucdn.com/icon_sale2.png"}]
                 * activity_rule :
                 */
                private int status;
                private String product_name;
                private String brand_id;
                private int stock_num;
                private String product_url;
                private String brandName;
                private String price;
                private String brandUrl;
                private String market_price;
                private String product_sys_code;
                private String on_sale_date;
                private List<String> spec_price_list;
                private String activity_icon;
                private List<ProdClsTagEntity> prodClsTag;
                private String activity_rule;

                public void setStatus(int status) {
                    this.status = status;
                }

                public void setProduct_name(String product_name) {
                    this.product_name = product_name;
                }

                public void setBrand_id(String brand_id) {
                    this.brand_id = brand_id;
                }

                public void setStock_num(int stock_num) {
                    this.stock_num = stock_num;
                }

                public void setProduct_url(String product_url) {
                    this.product_url = product_url;
                }

                public void setBrandName(String brandName) {
                    this.brandName = brandName;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public void setBrandUrl(String brandUrl) {
                    this.brandUrl = brandUrl;
                }

                public void setMarket_price(String market_price) {
                    this.market_price = market_price;
                }

                public void setProduct_sys_code(String product_sys_code) {
                    this.product_sys_code = product_sys_code;
                }

                public void setOn_sale_date(String on_sale_date) {
                    this.on_sale_date = on_sale_date;
                }

                public void setSpec_price_list(List<String> spec_price_list) {
                    this.spec_price_list = spec_price_list;
                }

                public void setActivity_icon(String activity_icon) {
                    this.activity_icon = activity_icon;
                }

                public void setProdClsTag(List<ProdClsTagEntity> prodClsTag) {
                    this.prodClsTag = prodClsTag;
                }

                public void setActivity_rule(String activity_rule) {
                    this.activity_rule = activity_rule;
                }

                public int getStatus() {
                    return status;
                }

                public String getProduct_name() {
                    return product_name;
                }

                public String getBrand_id() {
                    return brand_id;
                }

                public int getStock_num() {
                    return stock_num;
                }

                public String getProduct_url() {
                    return product_url;
                }

                public String getBrandName() {
                    return brandName;
                }

                public String getPrice() {
                    return price;
                }

                public String getBrandUrl() {
                    return brandUrl;
                }

                public String getMarket_price() {
                    return market_price;
                }

                public String getProduct_sys_code() {
                    return product_sys_code;
                }

                public String getOn_sale_date() {
                    return on_sale_date;
                }

                public List<String> getSpec_price_list() {
                    return spec_price_list;
                }

                public String getActivity_icon() {
                    return activity_icon;
                }

                public List<ProdClsTagEntity> getProdClsTag() {
                    return prodClsTag;
                }

                public String getActivity_rule() {
                    return activity_rule;
                }

                public class ProdClsTagEntity {
                    /**
                     * tagType : 0
                     * remark :
                     * tagName : 折扣
                     * tagUrl : http://metersbonwe.qiniucdn.com/icon_sale2.png
                     */
                    private int tagType;
                    private String remark;
                    private String tagName;
                    private String tagUrl;

                    public void setTagType(int tagType) {
                        this.tagType = tagType;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }

                    public void setTagName(String tagName) {
                        this.tagName = tagName;
                    }

                    public void setTagUrl(String tagUrl) {
                        this.tagUrl = tagUrl;
                    }

                    public int getTagType() {
                        return tagType;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public String getTagName() {
                        return tagName;
                    }

                    public String getTagUrl() {
                        return tagUrl;
                    }
                }
            }
        }
    }
}
