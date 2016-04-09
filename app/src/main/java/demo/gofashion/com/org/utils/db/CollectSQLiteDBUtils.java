package demo.gofashion.com.org.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/26.
 * 用于调用增删改的工具类
 */
public class CollectSQLiteDBUtils {

    //收藏表
    private CollectTable collectTable;

    /**
     * 初始化数据
     *
     * @param context 传进来的上下文
     */
    public CollectSQLiteDBUtils(Context context) {
        collectTable = new CollectTable(context);
    }

    /**
     * 将数据加到表中 返回的是一个影响数据，要判断是否大于0
     */
    public long addAllCloumnData(String codeId, String product_name, byte[] arrBitmap, String url, String price) {
        //表示就算内存不在够用，也能读
        SQLiteDatabase db = collectTable.getReadableDatabase();
        long rowd = -1;
        ContentValues values = new ContentValues();
        values.put("_id", codeId);
        values.put("product_name", product_name);
        values.put("bitmap", arrBitmap);
        values.put("url", url);
        values.put("price", price);
        rowd = db.insert("collect", null, values);
        db.close();
        return rowd;
    }

    /**
     * 该方法用于执行增删改的sql语句
     *
     * @param sql      sql语句
     * @param bindArgs sql语句中要绑定(占位符)的参数值
     * @return boolean
     */
    public boolean executeSql(String sql, Object[] bindArgs) {
        //表示就算内存不在够用，也能读
        SQLiteDatabase db = collectTable.getReadableDatabase();
        try {
            if (db != null) {
                if (bindArgs == null) {
                    db.execSQL(sql);
                } else {
                    db.execSQL(sql, bindArgs);
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    // 查询读取表中的数据返回Cursor
    public Cursor findSelectCursor(String sql,
                                   String[] selectionArgs) {
        //表示就算内存不在够用，也能读
        SQLiteDatabase db = collectTable.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }

    // 读取表中的数据返回List<Map<String,String>>
    public List<Map<String, Object>> findAllCloumnData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        // dbHelper.getReadableDatabase() : 如果空间不足，不能写，只能读
        // dbHelper.getWritableDatabase(); 如果空间不足,既不能写，也不能读(报错)
        //表示就算内存不在够用，也能读
        SQLiteDatabase db = collectTable.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from collect", null);
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    if (i == 2) {
                        map.put(cursor.getColumnName(i), cursor.getBlob(i));
                    } else {
                        map.put(cursor.getColumnName(i), cursor.getString(i));
                    }
                }
                list.add(map);

        }
        cursor.close();

        db.close();

        return list;
    }

}
