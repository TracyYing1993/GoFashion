package demo.gofashion.com.org.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import demo.gofashion.com.org.adapter.CollectBaseAdapter;
import demo.gofashion.com.org.utils.db.CollectSQLiteDBUtils;

/**
 * Created by Administrator on 2016/3/26.
 * 收藏Activity
 */
public class CollectActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = CollectActivity.class.getSimpleName();
    private TextView text_empty;
    private GridView gridView_cllect;
    private TextView text_record_count;
    private CollectSQLiteDBUtils db;

    private CollectBaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect_layout_activity);
        db=new CollectSQLiteDBUtils(this);
        //空数据时显示提示
        text_empty = ((TextView) findViewById(R.id.text_empty_cllect));
         //Listview
        gridView_cllect = ((GridView) findViewById(R.id.gridView_cllect));
        //记录数
        text_record_count = ((TextView) findViewById(R.id.text_record_count));

        gridView_cllect.setEmptyView(text_empty);

        Cursor cursor = db.findSelectCursor("select count(*) from collect", null);
        cursor.moveToFirst();

        // 查出所有的记录数
        int recordCount = cursor.getInt(0);
        text_record_count.setText("共有:" + recordCount + " 条记录");
        cursor.close();

        registerForContextMenu(gridView_cllect);

        initData();

        gridView_cllect.setOnItemClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.clear_all_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clearAll:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.ico_collect);
                builder.setTitle("清空记录：");
                builder.setMessage("确认要清空所有收藏记录吗？");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean result = db.executeSql("delete from collect", null);
                        if (result) {
                            Toast.makeText(getApplicationContext(), "清空成功",
                                    Toast.LENGTH_SHORT).show();
                            fillDataBaseRefresh();
                        } else {
                            Toast.makeText(getApplicationContext(), "清空失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Map<String, String> map = (Map<String, String>) adapter
                .getItem(info.position);
        menu.setHeaderTitle(map.get("product_name"));
        // 填充菜单
        getMenuInflater().inflate(R.menu.delete_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        Map<String, String> map = (Map<String, String>) adapter
                .getItem(info.position);
        final String id = map.get("_id");
        switch (item.getItemId()) {
            case R.id.delete:// 删除
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.ico_collect);
                builder.setTitle("删除记录：");
                builder.setMessage("确认要删除该记录吗？");
                builder.setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("temp", id + ".......");
                        boolean result = db.executeSql("delete from collect where _id=?",
                                new String[] { id });
                        if (result) {
                            Toast.makeText(getApplicationContext(), "删除成功",
                                    Toast.LENGTH_SHORT).show();
                            fillDataBaseRefresh();

                        } else {
                            Toast.makeText(getApplicationContext(), "删除失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();

                break;

        }
        return super.onContextItemSelected(item);
    }


    /**
     * 该方法是用于更新listView中的数据和将数据库中的数据用SimpleCursorAdapter进行将数据绑定到listView中
     *
     */
    public void fillDataBaseRefresh() {
        //获得用集合封装的数据
        List<Map<String, Object>> data = db.findAllCloumnData();
        adapter=new CollectBaseAdapter(data,this);
        gridView_cllect.setAdapter(adapter);

        // 查出所有的记录数
        Cursor cursor2 = db.findSelectCursor("select count(*) from collect", null);
        cursor2.moveToFirst();
        int count = cursor2.getInt(0);// 就在第一列
        text_record_count.setText("共有:" + count + " 条访问记录");
        cursor2.close();
    }

    private void initData() {
        //获得用集合封装的数据
        List<Map<String, Object>> data = db.findAllCloumnData();
        if(data!=null) {
            for (int i = 0; i < data.size(); i++) {
                Log.e(TAG, "initData: "+data.get(i) );
            }
            adapter = new CollectBaseAdapter(data,this);
            gridView_cllect.setAdapter(adapter);
        }
    }

    public void clickButton(View view) {
        this.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) adapter
                .getItem(position);
        String url = map.get("url").toString();
        Intent intent = new Intent(this, GridViewDetailActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
