package com.wngj.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * 
 * 
 * @author
 * 
 */
public class NetworkUtil {

	public static boolean isNetworkValidate(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isConnected()
				&& cm.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) {
			return cm.getActiveNetworkInfo().isAvailable();
		}
		return false;
	}
}
