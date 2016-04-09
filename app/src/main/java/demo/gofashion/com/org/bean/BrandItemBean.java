package demo.gofashion.com.org.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/24.
 */
public class BrandItemBean implements Serializable{
    private String ename;
    private String id;
    private int index;
    private String temp_ip;
    private String imgurl;
    private String brand_code;

    public String getBrand_code() {
        return brand_code;
    }

    public void setBrand_code(String brand_code) {
        this.brand_code = brand_code;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTemp_ip() {
        return temp_ip;
    }

    public void setTemp_ip(String temp_ip) {
        this.temp_ip = temp_ip;
    }

    @Override
    public String toString() {
        return "BrandItemBean{" +
                "ename='" + ename + '\'' +
                ", id='" + id + '\'' +
                ", index=" + index +
                ", temp_ip='" + temp_ip + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }
}
