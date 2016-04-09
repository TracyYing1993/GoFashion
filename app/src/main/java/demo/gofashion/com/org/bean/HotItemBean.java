package demo.gofashion.com.org.bean;

/**
 * Created by Administrator on 2016/3/24.
 */
public class HotItemBean {
    private String id;
    private int index;
    private String info;
    private String name;
    private String temp_id;
    private String type;
    private String imgUrl;

    public String  getId() {
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp_id() {
        return temp_id;
    }

    public void setTemp_id(String temp_id) {
        this.temp_id = temp_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "HotItemBean{" +
                "id='" + id + '\'' +
                ", index=" + index +
                ", info='" + info + '\'' +
                ", name='" + name + '\'' +
                ", temp_id='" + temp_id + '\'' +
                ", type='" + type + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
