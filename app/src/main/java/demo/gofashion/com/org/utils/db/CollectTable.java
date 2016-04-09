package demo.gofashion.com.org.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/3/26.
 */
public class CollectTable extends SQLiteOpenHelper {
    public final static String TABLE_NAME = "collect.db";
    public final static int VERSION = 1;

    public CollectTable(Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据库中的访问记录
        db.execSQL("create table collect "
                + " (_id varchar(100) primary key  not null,"
                + " product_name varchar(100) not null," +
                " bitmap BLOB not null," +
                " url varchar(100)  not null," +
                "price varchar(50) not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<newVersion){
            db.execSQL("drop table if exists collect");
            onCreate(db);
        }
    }
}
