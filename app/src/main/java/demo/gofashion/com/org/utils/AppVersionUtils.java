package demo.gofashion.com.org.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class AppVersionUtils {
	
	private Context context;

	public AppVersionUtils(Context context) {
		this.context = context;
	}

	public String getApplicationVersion(){
		PackageManager manager=context.getPackageManager();
		PackageInfo packageInfo=null;
		try {
			packageInfo=manager.getPackageInfo(context.getPackageName(), 0);
			
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return packageInfo.versionName;
	}
	

}
