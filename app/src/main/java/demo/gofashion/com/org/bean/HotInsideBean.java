package demo.gofashion.com.org.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class HotInsideBean {

    /**
     * status : 1
     * data : [{"id":"24","sort":"2","level":"2","subs":[{"id":"166","sort":"1","level":"3","name":"开衫毛衣","temp_id":"166","url":"http://metersbonwe.qiniucdn.com/nvzhuang823.jpg","parent_id":"24"},{"id":"168","sort":"2","level":"3","name":"套头毛衣","temp_id":"168","url":"http://metersbonwe.qiniucdn.com/nvzhuang825.jpg","parent_id":"24"}],"name":"毛衣","temp_id":"24","url":"http://metersbonwe.qiniucdn.com/nvzhuang823.jpg","parent_id":"4"}]
     * info :
     */
    private int status;
    private List<DataEntity> data;
    private String info;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public class DataEntity {
        /**
         * id : 24
         * sort : 2
         * level : 2
         * subs : [{"id":"166","sort":"1","level":"3","name":"开衫毛衣","temp_id":"166","url":"http://metersbonwe.qiniucdn.com/nvzhuang823.jpg","parent_id":"24"},{"id":"168","sort":"2","level":"3","name":"套头毛衣","temp_id":"168","url":"http://metersbonwe.qiniucdn.com/nvzhuang825.jpg","parent_id":"24"}]
         * name : 毛衣
         * temp_id : 24
         * url : http://metersbonwe.qiniucdn.com/nvzhuang823.jpg
         * parent_id : 4
         */
        private String id;
        private String sort;
        private String level;
        private List<SubsEntity> subs;
        private String name;
        private String temp_id;
        private String url;
        private String parent_id;

        public void setId(String id) {
            this.id = id;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public void setSubs(List<SubsEntity> subs) {
            this.subs = subs;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTemp_id(String temp_id) {
            this.temp_id = temp_id;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getId() {
            return id;
        }

        public String getSort() {
            return sort;
        }

        public String getLevel() {
            return level;
        }

        public List<SubsEntity> getSubs() {
            return subs;
        }

        public String getName() {
            return name;
        }

        public String getTemp_id() {
            return temp_id;
        }

        public String getUrl() {
            return url;
        }

        public String getParent_id() {
            return parent_id;
        }

        public class SubsEntity {
            /**
             * id : 166
             * sort : 1
             * level : 3
             * name : 开衫毛衣
             * temp_id : 166
             * url : http://metersbonwe.qiniucdn.com/nvzhuang823.jpg
             * parent_id : 24
             */
            private String id;
            private String sort;
            private String level;
            private String name;
            private String temp_id;
            private String url;
            private String parent_id;

            public void setId(String id) {
                this.id = id;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setTemp_id(String temp_id) {
                this.temp_id = temp_id;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getId() {
                return id;
            }

            public String getSort() {
                return sort;
            }

            public String getLevel() {
                return level;
            }

            public String getName() {
                return name;
            }

            public String getTemp_id() {
                return temp_id;
            }

            public String getUrl() {
                return url;
            }

            public String getParent_id() {
                return parent_id;
            }
        }
    }
}
