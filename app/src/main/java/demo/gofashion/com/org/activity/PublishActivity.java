package demo.gofashion.com.org.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import demo.gofashion.com.org.utils.AppVersionUtils;

/**
 * Created by Administrator on 2016/3/26.
 */
public class PublishActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        TextView text_version=(TextView) this.findViewById(R.id.text_version);
        AppVersionUtils versionUtils=new AppVersionUtils(getApplicationContext());
        String version=versionUtils.getApplicationVersion();//获得版本信息
        text_version.setText("Android v" + version);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
