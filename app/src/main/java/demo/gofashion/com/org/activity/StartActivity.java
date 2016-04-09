package demo.gofashion.com.org.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import demo.gofashion.com.org.utils.db.SharedPfrUtils;

/**
 * Created by Administrator on 2016/3/24.
 * 每次开启软件时，并判断是否是第一次运行软件，看是跳入HomeActivty还是WelcomeActivity
 */
public class StartActivity extends AppCompatActivity {
    /** 保存是否是第一次运行程序的标记 */
    public final static String IS_FIRST_RUN = "isFirstRun";
    /** 不是第一次运行标识 */
    public final static int NOT_FIRST = 1;
    /** 是第一次运行标识 */
    public final static int IS_FIRST = -1;
    private SharedPfrUtils utils;
    /** 用于跳转到不同界面，并且延迟三秒 */
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_start);
        initData();
    }

    private void initData() {
        utils = new SharedPfrUtils(StartActivity.this,"SharedPreferencesHelper");
        ImageView img = (ImageView) findViewById(R.id.anim_start_img);
       img.setBackgroundResource(R.drawable.anim_start);
        AnimationDrawable anim = (AnimationDrawable) img.getBackground();
        anim.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        goActivity(HomeActivity.class);
                        break;
                    case 1:
                        goActivity(WelcomeActivity.class);
                        break;
                }
            };
        };
        int isFirst = utils.getInt(IS_FIRST_RUN);
        if (isFirst == NOT_FIRST){      // 判断是否是第一次运行
            handler.sendEmptyMessageDelayed(0,1760); // 延迟时间为2秒
        }else{
            handler.sendEmptyMessageDelayed(0,1760);
        }

    }

    // 路转到某页面

    protected void goActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        this.finish();
    }

}
