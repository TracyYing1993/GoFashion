package demo.gofashion.com.org.utils.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/***
 * 这是一个弹出退出对话框的工具类
 */
public class DialogUtil {

	public static AlertDialog.Builder getBuilderDilog(Context context, int icon,String title,String message){
		AlertDialog.Builder builder=new AlertDialog.Builder(context);
		builder.setIcon(icon);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setNegativeButton("取消", null);
		setPositiveButton(builder);
		builder.show();
		return builder;
	}

	public static AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder){//确定退出
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
			}
		});
		return builder;
	}

}
