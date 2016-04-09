package demo.gofashion.com.org.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/3/26.
 */
public class GoPiaoActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edit_gopiao;
    private Button goupiao_btn;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gopiao);

        edit_gopiao = ((EditText) findViewById(R.id.gopiao_edit));
        goupiao_btn = ((Button) findViewById(R.id.gopiao_btn));

        goupiao_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gopiao_btn:
                index ++;
                String text = edit_gopiao.getText().toString();
                if (!"".equals(text) && index%2 == 0){
                    startActivity(new Intent(GoPiaoActivity.this,LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(GoPiaoActivity.this, "请输入正确的兑换码", Toast.LENGTH_SHORT).show();
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
