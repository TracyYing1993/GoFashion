package demo.gofashion.com.org.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import demo.gofashion.com.org.utils.netutils.MyTask;

/**
 * Created by Administrator on 2016/3/25.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_login;
    private TextView login_back;
    private ImageView img_weixin,img_qq;
    private EditText edit_user,edit_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = ((Button) findViewById(R.id.btn_login));
        btn_login.setOnClickListener(this);
        login_back = ((TextView) findViewById(R.id.login_back));
        login_back.setOnClickListener(this);
        img_weixin = ((ImageView) findViewById(R.id.img_weixin));
        img_weixin.setOnClickListener(this);
        img_qq = ((ImageView) findViewById(R.id.img_qq));
        img_qq.setOnClickListener(this);

        edit_user = ((EditText) findViewById(R.id.edit_login_user));
        edit_pwd = ((EditText) findViewById(R.id.edit_login_psw));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_back:
                finish();
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.img_weixin:
                new MyTask(this).execute("http://www.baidu.com/");
                break;
            case R.id.img_qq:
                new MyTask(this).execute("http://www.baidu.com/");
                break;
        }
    }

    private void login() {
        String user = edit_user.getText().toString();
        String pwd = edit_pwd.getText().toString();
        if (!"".equals(user) && !"".equals(pwd)){
            new MyTask(this).execute("http://www.baidu.com/");
        }else{
            Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
        }
    }

    //判断是否为数字
    public  boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
