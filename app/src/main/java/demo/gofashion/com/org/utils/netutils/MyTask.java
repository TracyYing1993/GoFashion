package demo.gofashion.com.org.utils.netutils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/3/22.
 * 带有ProgressDialog的AsyncTask类
 */
public class MyTask extends AsyncTask<String, Void, String> {
    private ProgressDialog pDialog;
    private Context context;
    public MyTask(Context context) {
        this.context = context;
        pDialog = new ProgressDialog(context);
        pDialog.setTitle("提示：");
        pDialog.setMessage("正在登录......");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        Request request = new Request.Builder().url(params[0]).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()){
                return  "登录成功";
            }else{
                return "登录失败";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String bytes) {
        super.onPostExecute(bytes);
        pDialog.dismiss();
        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
    }
}
