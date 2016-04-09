package demo.gofashion.com.org.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/3/26.
 * 左边菜单测验值所跳转的activity
 */
public class CeYanZhiActivtiy extends AppCompatActivity implements View.OnClickListener{
    private ImageView img_ceyan,img_background,img_photo;
    private Button btn_ceyan,btn_shangchuan;
    private Bitmap bitmap = null;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceyanzhi);

        text = ((TextView) findViewById(R.id.text_camera));
        img_ceyan = ((ImageView) findViewById(R.id.ceyan_img));
        img_background = ((ImageView) findViewById(R.id.image_background));
        img_photo = ((ImageView) findViewById(R.id.imageview_camera));

        img_photo.setOnClickListener(this);
        btn_ceyan = ((Button) findViewById(R.id.btn_ceyan));
        btn_ceyan.setOnClickListener(this);
        btn_shangchuan = ((Button) findViewById(R.id.btn_up));
        btn_shangchuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ceyan:
                img_ceyan.setVisibility(View.GONE);
                btn_ceyan.setVisibility(View.GONE);
                img_background.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                img_photo.setVisibility(View.VISIBLE);
                btn_shangchuan.setVisibility(View.VISIBLE);
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
                break;
            case R.id.btn_up:
                if (bitmap != null) {
                    goActivity(LoginActivity.class);
                }
                break;
            case R.id.imageview_camera:
                Intent intent1 = new Intent();
                intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent1,1);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            bitmap = (Bitmap)data.getExtras().get("data");
            img_photo.setImageBitmap(bitmap);
        }
    }

    //跳转到某Activtiy

    protected void goActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }


        @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
