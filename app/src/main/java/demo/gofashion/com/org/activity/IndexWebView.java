package demo.gofashion.com.org.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/3/24.
 */
public class IndexWebView extends AppCompatActivity {
    private static final String TAG =IndexWebView.class.getSimpleName() ;
    private WebView webview;
    private TextView text_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_webview_layout);
        webview = ((WebView) findViewById(R.id.index_webview));
        text_title = ((TextView) findViewById(R.id.text_title_index));
        //获得数据
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            text_title.setText(bundle.getString("title"));
            String url = bundle.getString("url");
            webview.loadUrl(url);
            Log.e(TAG, "onCreate: url="+url);
        }
    }

    public void clickButton(View view){

        switch (view.getId()){
            case R.id.btn_share_index:
                //分享
                break;
            case R.id.btn_back_index:
                //回到主Activity
                startActivity(new Intent(this,HomeActivity.class));
                finish();
                break;
            case R.id.btn_top_index:
                //到顶部
                break;
        }
    }
}
