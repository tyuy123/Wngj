package com.wngj;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by NiKOo on 16/11/30.
 */

public class RestClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, Context context,
                           AsyncHttpResponseHandler responseHandler) {
        if (NetworkUtil.isNetworkValidate(context)) {
            try {
                client.setURLEncodingEnabled(false);
                client.get(url, null, responseHandler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(context, "网络连接不上", Toast.LENGTH_LONG).show();
        }
    }
}
