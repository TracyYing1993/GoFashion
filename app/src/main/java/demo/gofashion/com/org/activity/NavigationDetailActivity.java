package demo.gofashion.com.org.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import demo.gofashion.com.org.utils.QRCodeUtil;
import demo.gofashion.com.org.zxing.activity.CaptureActivity;

/**
 * Created by Administrator on 2016/3/26.
 *
 * 扫一扫
 */
public class NavigationDetailActivity extends AppCompatActivity {

    private TextView tv_scanned;
    private ImageView ivScan;
    private Button btn_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        tv_scanned = ((TextView) findViewById(R.id.tvScanned));
        ivScan = ((ImageView) findViewById(R.id.ivScan));
        btn_scan = ((Button) findViewById(R.id.btnScan));
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(NavigationDetailActivity.this, CaptureActivity.class),100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    //显示扫描到的内容
                    tv_scanned.setText(bundle.getString("result"));
                    //显示
                    Bitmap bitmap = QRCodeUtil.createCodeBitmap(this, bundle.getString("result"), 350);
                   // ivScan.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                    ivScan.setImageBitmap(bitmap);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
