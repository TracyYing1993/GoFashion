package demo.gofashion.com.org.utils.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/3/24.
 * 数据持久化存储Url
 */
public class SharedPfrUtils {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SharedPfrUtils(Context context,String spfName) {
        prefs = context.getSharedPreferences(spfName, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    //根据键字符串存取一个字符串值
    public boolean putString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    //根据key值的到存储结果，如果没找到value则返回null
    public String getString(String key) {
        return prefs.getString(key, null);
    }


    //根据键字符串，存储一个字符串值
    public boolean putInt(String key, int value) {
        editor.putInt(key, value);
        return editor.commit();
    }

    //根据key值得到存储结果，如果没有找到value就返回-1
    public int getInt(String key) {
        return prefs.getInt(key, -1);
    }

    //清空数据
    public boolean clear() {
        editor.clear();
        return editor.commit();
    }

    //关闭当前对象
    public void close() {
        prefs = null;
    }
}

