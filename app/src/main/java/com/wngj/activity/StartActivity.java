package com.wngj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.wngj.R;

/**
 * Created by NiKOo on 16/12/6.
 */

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        }.sendEmptyMessageDelayed(1, 1000);
    }


}
