package com.wngj;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.wngj.wxapi.ShareListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private GridView gridView;
    private MyAdapter myAdapter;
    private List<MyBean> mList = new ArrayList<MyBean>();
    private String shareUrl;
    private TextView tv_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridview);
        tv_share = (TextView) findViewById(R.id.tv_share);
        tv_share.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String link = mList.get(position).link;
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });
        getData();
        //配置微信分享
        PlatformConfig.setWeixin("wx64d825d616bc3349", "85e642df508e49cbd45472752a9a2637");
        //初始化友盟分享SDK
        UMShareAPI.get(this);
    }

    private void getData() {
        String url = "http://app.vsucai.cn/index.php?s=/Home/User/wan&page=1&size=1000";
        RestClient.get(url, this, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject j = new JSONObject(result);
                    if (j.getString("code").equals("0")){
                        mList = JSON.parseArray(j.getJSONObject("data").getString("list"), MyBean.class);
                        shareUrl = j.getJSONObject("data").getString("shareurl");//分享链接
                        if (mList != null){
                            myAdapter = new MyAdapter(MainActivity.this, mList);
                            gridView.setAdapter(myAdapter);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_share:
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//                intent.putExtra(Intent.EXTRA_TEXT, shareUrl+" -- 万能工具");
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(Intent.createChooser(intent, getTitle()));

                new ShareAction(this).setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withTitle("我的万能工具")
                        .withText("万能工具")
                        .withTargetUrl(shareUrl)
                        .withMedia(new UMImage(this, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)))
                        .setCallback(new ShareListener(this))
                        .open();
                break;
        }
    }
}
