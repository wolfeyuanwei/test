package com.cdjiahotx.mobilephoneclient.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cdjiahotx.mobilephoneclient.R;
import com.cdjiahotx.mobilephoneclient.domain.AppInfo;
import com.cdjiahotx.mobilephoneclient.ui.ChatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.SmsMessage;
import android.text.format.DateFormat;
import android.util.Log;

public class PhoneControlUtils {
	private static String[][] MIME_MapTable = {
			// {后缀名， MIME类型}
			{ ".3gp", "video/3gpp" },
			{ ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" },
			{ ".avi", "video/x-msvideo" },
			{ ".bin", "application/octet-stream" },
			{ ".bmp", "image/bmp" },
			{ ".c", "text/plain" },
			{ ".class", "application/octet-stream" },
			{ ".conf", "text/plain" },
			{ ".cpp", "text/plain" },
			{ ".doc", "application/msword" },
			{ ".docx",
					"application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
			{ ".xls", "application/vnd.ms-excel" },
			{ ".xlsx",
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
			{ ".exe", "application/octet-stream" },
			{ ".gif", "image/gif" },
			{ ".gtar", "application/x-gtar" },
			{ ".gz", "application/x-gzip" },
			{ ".h", "text/plain" },
			{ ".htm", "text/html" },
			{ ".html", "text/html" },
			{ ".jar", "application/java-archive" },
			{ ".java", "text/plain" },
			{ ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" },
			{ ".js", "application/x-javascript" },
			{ ".log", "text/plain" },
			{ ".m3u", "audio/x-mpegurl" },
			{ ".m4a", "audio/mp4a-latm" },
			{ ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" },
			{ ".m4u", "video/vnd.mpegurl" },
			{ ".m4v", "video/x-m4v" },
			{ ".mov", "video/quicktime" },
			{ ".mp2", "audio/x-mpeg" },
			{ ".mp3", "audio/x-mpeg" },
			{ ".mp4", "video/mp4" },
			{ ".mpc", "application/vnd.mpohun.certificate" },
			{ ".mpe", "video/mpeg" },
			{ ".mpeg", "video/mpeg" },
			{ ".mpg", "video/mpeg" },
			{ ".mpg4", "video/mp4" },
			{ ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" },
			{ ".ogg", "audio/ogg" },
			{ ".pdf", "application/pdf" },
			{ ".png", "image/png" },
			{ ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" },
			{ ".pptx",
					"application/vnd.openxmlformats-officedocument.presentationml.presentation" },
			{ ".prop", "text/plain" }, { ".rc", "text/plain" },
			{ ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" },
			{ ".sh", "text/plain" }, { ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" },
			{ ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" },
			{ ".wmv", "audio/x-ms-wmv" },
			{ ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" },
			{ ".z", "application/x-compress" },
			{ ".zip", "application/x-zip-compressed" }, { "", "*/*" } };

	public PhoneControlUtils() {

	}

	/**
	 * 检查当前WIFI是否连接，两层意思——是否连接，连接是不是WIFI
	 * 
	 * @param context
	 * @return true表示当前网络处于连接状态，且是WIFI，否则返回false
	 */
	public static boolean isWifiConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()
				&& ConnectivityManager.TYPE_WIFI == info.getType()) {
			return true;
		}
		return false;
	}

	/**
	 * 检查当前GPRS是否连接，两层意思——是否连接，连接是不是GPRS
	 * 
	 * @param context
	 * @return true表示当前网络处于连接状态，且是GPRS，否则返回false
	 */
	public static boolean isGprsConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()
				&& ConnectivityManager.TYPE_MOBILE == info.getType()) {
			return true;
		}
		return false;
	}

	/**
	 * 检查当前是否有网络连接
	 * 
	 * @param context
	 * @return true表示当前网络处于连接状态，，否则返回false
	 */
	public static boolean isConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			return true;
		}
		return false;
	}

	public static boolean isGPRSConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			Log.i("MainActivity", info.getTypeName());
			return true;
		}
		return false;
	}

	/**
	 * 改变数据通道状态
	 * 
	 * @param context
	 * @param enabled
	 *            true - 打开gprs连接设置，否则关闭连接设置
	 */
	public static void toggleMobileData(Context context, boolean enabled) {
		ConnectivityManager conMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		Class<?> conMgrClass = null;
		Field iConMgrField = null;
		Object iConMgr = null;
		Class<?> iConMgrClass = null;
		Method setMobileDataEnabledMethod = null;

		try {
			conMgrClass = Class.forName(conMgr.getClass().getName());
			iConMgrField = conMgrClass.getDeclaredField("mService");
			iConMgrField.setAccessible(true);
			iConMgr = iConMgrField.get(conMgr);
			iConMgrClass = Class.forName(iConMgr.getClass().getName());
			setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod(
					"setMobileDataEnabled", Boolean.TYPE);
			setMobileDataEnabledMethod.setAccessible(true);
			setMobileDataEnabledMethod.invoke(iConMgr, enabled);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置Wifi的状态（打开或是关闭wifi）
	 * 
	 * @param context
	 * @param status
	 *            - true 打开wifi；否则false
	 */
	public static void toggleWifi(Context context, boolean status) {
		WifiManager wm;
		wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		if (status) {
			if (!wm.isWifiEnabled()) {
				wm.setWifiEnabled(true);
			}
		} else {
			if (wm.isWifiEnabled()) {
				wm.setWifiEnabled(false);
			}
		}
	}

	/**
	 * 判断服务是否运行 className -- 必须要写全类名。如：com.cdjiahotx.mobilephoneclient.service.
	 * PhStatusMonitorService
	 * 
	 * @return
	 */
	public static boolean isServiceRunning(Context mContext, String className) {

		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(30);

		if (!(serviceList.size() > 0)) {
			return false;
		}

		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}

	/**
	 * 设置蓝牙状态
	 * 
	 * @param context
	 * @param status
	 *            - true 打开蓝牙设置；否则false
	 */
	public static void toggleBlueTooth(Context context, boolean status) {
		BluetoothAdapter adapter;
		adapter = BluetoothAdapter.getDefaultAdapter();
		if (status) {
			if (adapter.getState() == BluetoothAdapter.STATE_OFF) {
				adapter.enable();
			}
		} else {
			if (adapter.getState() == BluetoothAdapter.STATE_ON) {
				adapter.disable();
			}
		}
	}

	/**
	 * 获取SD卡路径
	 * 
	 * @return
	 */
	public static String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = sdCardExist();
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();
		}

		return sdDir.toString();
	}

	/**
	 * 判断SD卡是否存在
	 * 
	 * @return ture - sdcard可以； false - sdcard不可用
	 */
	public static boolean sdCardExist() {
		boolean sdExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);

		return sdExist;
	}

	/**
	 * 获取短信的内容
	 * 
	 * @param intent
	 * @return
	 */
	public static String getSMSContent(Intent intent) {
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");
		String content = null;
		for (Object pdu : pdus) {
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
			content = sms.getMessageBody();
		}
		return content;
	}

	/**
	 * 获取一个随机字符串
	 * 
	 * @return
	 */
	public static String getUUID() {
		String result = null;
		result = UUID.randomUUID().toString().replaceAll("-", "")
				.substring(0, 25);
		return result;
	}

	/**
	 * 获取文件的类型（后缀名）
	 * 
	 * @param name
	 * @return
	 */
	public static String getFileType(String name) {
		int pos = name.lastIndexOf(".");
		if (pos == -1) {
			return null;
		}
		return name.substring(pos, name.length());
	}

	/**
	 * 获取当前系统所有的包的信息
	 * 
	 * @return
	 */
	public static List<AppInfo> getAllApps(Context context) {
		PackageManager packmanager = context.getPackageManager();
		List<AppInfo> appinfos = new ArrayList<AppInfo>();
		List<PackageInfo> packinfos = packmanager
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		for (PackageInfo info : packinfos) {
			AppInfo myApp = new AppInfo();
			String packname = info.packageName;
			myApp.setPackname(packname);
			ApplicationInfo appinfo = info.applicationInfo;
//			Drawable icon = appinfo.loadIcon(packmanager);
//			myApp.setIcon(icon);
			String appname = appinfo.loadLabel(packmanager).toString();
			myApp.setAppname(appname);
			if (filterApp(appinfo)) {
				myApp.setSystemApp(false);
			} else {
				myApp.setSystemApp(true);
			}
			appinfos.add(myApp);
		}
		return appinfos;
	}

	/**
	 * 判断应用是否是一个系统应用
	 * 
	 * @param info
	 * @return true - 系统应用；否则false
	 */
	public static boolean filterApp(ApplicationInfo info) {
		if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
			return true;
		} else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 从时间的毫秒数字符串转换成标准的时间格式
	 * 
	 * @param msec
	 *            - 毫秒数字符串
	 * @return
	 */
	public static String msec2Time(String msec) {
		long msecond = Long.parseLong(msec);

		// DateFormat df = new DateFormat();
		return DateFormat.format("yyyy-MM-dd hh-mm-ss", msecond).toString();
	}

	/**
	 * 获取应用程序版本号
	 * 
	 * @return
	 */
	public static String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			return info.versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 播放音乐
	 * 
	 * @param context
	 * @param sourceId
	 */
	public static void playSound(Context context, int sourceId) {
		MediaPlayer mediaplayer;
		mediaplayer = MediaPlayer.create(context, sourceId);
		mediaplayer.start();
	}

	/**
	 * 判断GPS是否打开
	 * 
	 * @param context
	 * @return true - 打开；false - 关闭
	 */
	public static boolean isOpenGps(Context context) {
		LocationManager manager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 打开GPS设置界面
	 * 
	 * @param context
	 */
	public static void openGPSSetting(Context context) {
		// Intent gpsSetting = new Intent(Settings.ACTION_SECURITY_SETTINGS);
		Intent gpsSetting = new Intent(
				"android.settings.LOCATION_SOURCE_SETTINGS");
		context.startActivity(gpsSetting);
	}

	/**
	 * 获取与文件对应的mime类型
	 * 
	 * @param file
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static String getMIMEType(File file) {

		String type = "*/*";
		String fName = file.getName();
		// 获取后缀名前的分隔符"."在fName中的位置。
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0) {
			return type;
		}
		/* 获取文件的后缀名 */
		// Implicitly using the default locale is a common source of bugs: Use
		// toLowerCase(Locale) instead
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		// 在MIME和文件类型的匹配表中找到对应的MIME类型。
		for (int i = 0; i < MIME_MapTable.length; i++) { // MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}

	/**
	 * 打开文件
	 * 
	 * @param file
	 */
	public static void openFile(Context context, File file) {

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// 设置intent的Action属性
		intent.setAction(Intent.ACTION_VIEW);
		// 获取文件file的MIME类型
		String type = getMIMEType(file);
		// 设置intent的data和Type属性。
		intent.setDataAndType(/* uri */Uri.fromFile(file), type);
		// 跳转
		context.startActivity(intent);

	}

	@SuppressWarnings("deprecation")
	public static void displayMessage(Context context, String mid, int aid) {
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// 定义Notification的各种属性
		Notification notification = new Notification(R.drawable.message,
				"你有新消息!", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_ONGOING_EVENT; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中
		notification.flags |= Notification.FLAG_AUTO_CANCEL; // 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		notification.defaults = Notification.DEFAULT_LIGHTS;
		notification.ledARGB = Color.BLUE;
		notification.ledOnMS = 5000;

		// 设置通知的事件消息
		CharSequence contentTitle = "你有新消息"; // 通知栏标题
		CharSequence contentText = ""; // 通知栏内容
		Intent notificationIntent = new Intent(context, ChatActivity.class); // 点击该通知后要跳转的Activity
		notificationIntent.putExtra("mid", mid);
		PendingIntent contentItent = PendingIntent.getActivity(context, aid,
				notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentItent);

		// 把Notification传递给NotificationManager
		notificationManager.notify(aid, notification);
	}

	/**
	 * 获取手机型号
	 * 
	 * @return - 手机型号
	 */
	public static String getPhoneType() {
		return android.os.Build.MODEL;
	}

}
