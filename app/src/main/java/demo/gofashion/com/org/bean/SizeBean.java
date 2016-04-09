package demo.gofashion.com.org.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public class SizeBean {

    /**
     * isSuccess : 1
     * message :
     * results : [{"product_sys_code":"885906","size_table":[["UK","尺码","中国码"],["6","39","245"],["6.5","40","245"],["7","40.5","250"],["7.5","41","255"],["8","42","260"],["8.5","42.5","265"],["9","43","265"],["9.5","44","270"],["10","44.5","275"],["10.5","45","280"],["11","46","285"]],"size_picture":"","sizE_JSON":[["UK","尺码","中国码"],["6","39","245"],["6.5","40","245"],["7","40.5","250"],["7.5","41","255"],["8","42","260"],["8.5","42.5","265"],["9","43","265"],["9.5","44","270"],["10","44.5","275"],["10.5","45","280"],["11","46","285"]]}]
     * total : 1
     */

    private int isSuccess;
    private String message;
    private int total;
    /**
     * product_sys_code : 885906
     * size_table : [["UK","尺码","中国码"],["6","39","245"],["6.5","40","245"],["7","40.5","250"],["7.5","41","255"],["8","42","260"],["8.5","42.5","265"],["9","43","265"],["9.5","44","270"],["10","44.5","275"],["10.5","45","280"],["11","46","285"]]
     * size_picture :
     * sizE_JSON : [["UK","尺码","中国码"],["6","39","245"],["6.5","40","245"],["7","40.5","250"],["7.5","41","255"],["8","42","260"],["8.5","42.5","265"],["9","43","265"],["9.5","44","270"],["10","44.5","275"],["10.5","45","280"],["11","46","285"]]
     */

    private List<ResultsEntity> results;

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public int getTotal() {
        return total;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public static class ResultsEntity {
        private String product_sys_code;
        private String size_picture;
        private List<List<String>> size_table;
        private List<List<String>> sizE_JSON;

        public void setProduct_sys_code(String product_sys_code) {
            this.product_sys_code = product_sys_code;
        }

        public void setSize_picture(String size_picture) {
            this.size_picture = size_picture;
        }

        public void setSize_table(List<List<String>> size_table) {
            this.size_table = size_table;
        }

        public void setSizE_JSON(List<List<String>> sizE_JSON) {
            this.sizE_JSON = sizE_JSON;
        }

        public String getProduct_sys_code() {
            return product_sys_code;
        }

        public String getSize_picture() {
            return size_picture;
        }

        public List<List<String>> getSize_table() {
            return size_table;
        }

        public List<List<String>> getSizE_JSON() {
            return sizE_JSON;
        }

        @Override
        public String toString() {
            return "ResultsEntity{" +
                    "product_sys_code='" + product_sys_code + '\'' +
                    ", size_picture='" + size_picture + '\'' +
                    ", size_table=" + size_table +
                    ", sizE_JSON=" + sizE_JSON +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SizeBean{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                ", total=" + total +
                ", results=" + results +
                '}';
    }
}
