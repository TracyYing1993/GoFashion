package demo.gofashion.com.org.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public class CommendBean {

    /**
     * data : [{"user_id":"1a63b104-55f8-c9d7-0ce0-e27db5befbe5","head_img":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM7LPevic24W0cib1Lla5ibibWgN8oLIELBcE8QmPOyyIO7OCz9SCdI4WsA0icS3axU95LicGhgxL6CJBnfA/0","head_v_type":"0","nick_name":"天天xiang上","id":"151143","score":"5","info":"好评~物美价廉!","create_time":"2016-03-18 12:27:14","to_user_id":"","to_user":{"nick_name":"","user_id":""}},{"user_id":"66871824-04e8-17e3-d919-47808c4e78e7","head_img":"http://q.qlogo.cn/qqapp/1102534852/3A61A1023AC9BCA7535FEB4855D2884C/160","head_v_type":"0","nick_name":"情挽颂","id":"149063","score":"5","info":"正品没问题，就是感觉小了，但是算了，少一个孔就还能穿了","create_time":"2016-03-14 11:36:14","to_user_id":"","to_user":{"nick_name":"","user_id":""}},{"user_id":"15ce754b-151f-443c-b16b-3d520c8c7eaf","head_img":"http://q.qlogo.cn/qqapp/1102534852/C2E4425336C5A046C4729B395265762C/100","head_v_type":"0","nick_name":"","id":"148763","score":"1","info":"东西一般,没有标签,鞋盒是坏的。","create_time":"2016-03-13 20:55:09","to_user_id":"","to_user":{"nick_name":"","user_id":""}},{"user_id":"bc500dfe-b8a8-4099-a2af-dee10d899d21","head_img":"http://q.qlogo.cn/qqapp/1102534852/B22F40CB5262E537208CC2546CEBB377/100","head_v_type":"0","nick_name":"玄隐皓月","id":"127671","score":"5","info":"棒棒的","create_time":"2016-02-01 21:01:19","to_user_id":"","to_user":{"nick_name":"","user_id":""}},{"user_id":"bc500dfe-b8a8-4099-a2af-dee10d899d21","head_img":"http://q.qlogo.cn/qqapp/1102534852/B22F40CB5262E537208CC2546CEBB377/100","head_v_type":"0","nick_name":"玄隐皓月","id":"127669","score":"5","info":"棒棒的","create_time":"2016-02-01 21:01:08","to_user_id":"","to_user":{"nick_name":"","user_id":""}}]
     * info :
     * status : 1
     */

    private String info;
    private int status;
    /**
     * user_id : 1a63b104-55f8-c9d7-0ce0-e27db5befbe5
     * head_img : http://wx.qlogo.cn/mmopen/Q3auHgzwzM7LPevic24W0cib1Lla5ibibWgN8oLIELBcE8QmPOyyIO7OCz9SCdI4WsA0icS3axU95LicGhgxL6CJBnfA/0
     * head_v_type : 0
     * nick_name : 天天xiang上
     * id : 151143
     * score : 5
     * info : 好评~物美价廉!
     * create_time : 2016-03-18 12:27:14
     * to_user_id :
     * to_user : {"nick_name":"","user_id":""}
     */

    private List<DataEntity> data;

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public int getStatus() {
        return status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String user_id;
        private String head_img;
        private String head_v_type;
        private String nick_name;
        private String id;
        private String score;
        private String info;
        private String create_time;
        private String to_user_id;
        /**
         * nick_name :
         * user_id :
         */

        private ToUserEntity to_user;

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public void setHead_v_type(String head_v_type) {
            this.head_v_type = head_v_type;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setTo_user_id(String to_user_id) {
            this.to_user_id = to_user_id;
        }

        public void setTo_user(ToUserEntity to_user) {
            this.to_user = to_user;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getHead_img() {
            return head_img;
        }

        public String getHead_v_type() {
            return head_v_type;
        }

        public String getNick_name() {
            return nick_name;
        }

        public String getId() {
            return id;
        }

        public String getScore() {
            return score;
        }

        public String getInfo() {
            return info;
        }

        public String getCreate_time() {
            return create_time;
        }

        public String getTo_user_id() {
            return to_user_id;
        }

        public ToUserEntity getTo_user() {
            return to_user;
        }

        public static class ToUserEntity {
            private String nick_name;
            private String user_id;

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getNick_name() {
                return nick_name;
            }

            public String getUser_id() {
                return user_id;
            }
        }
    }
}
