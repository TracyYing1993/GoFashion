package demo.gofashion.com.org.bean;

import android.widget.ImageView;

/**
 * Created by Administrator on 2016/3/25.
 */
public class ClassifyItemBean {
    private String urlImg;
    private String name;
    private int sale_price;

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    @Override
    public String toString() {
        return "ClassifyItemBean{" +
                "urlImg='" + urlImg + '\'' +
                ", name='" + name + '\'' +
                ", sale_price=" + sale_price +
                '}';
    }
}
