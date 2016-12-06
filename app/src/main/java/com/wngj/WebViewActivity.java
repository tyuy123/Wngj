package com.wngj;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by NiKOo on 16/11/30.
 */
public class WebViewActivity extends Activity {

    WebView webview;
    ProgressBar pb_use;

    String mLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mLink = getIntent().getStringExtra("link");
        initWebview();
    }

    private void initWebview() {
        webview = (WebView) findViewById(R.id.webview);
        pb_use = (ProgressBar) findViewById(R.id.pb_use);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb_use.setProgress(newProgress);
                if (newProgress == 100) {
                    pb_use.setVisibility(View.GONE);
                } else {
                    pb_use.setVisibility(View.VISIBLE);
                }
            }
        });
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webview.loadUrl(mLink);
        String html = "<html>" +
                "<body>" +
                " <script async src=\"http://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>" +
                "<ins class=\"adsbygoogle\"" +
                "     style=\"display:block\"" +
                "     data-ad-client=\"ca-pub-5667213825701714\"" +
                "     data-ad-slot=\"7086362816\"" +
                "     data-ad-format=\"auto\"></ins>" +
                "<script>" +
                "(adsbygoogle = window.adsbygoogle || []).push({});" +
                "</script>" +
                "</body>" +
                "</html>";
//        webview.loadData(html, "text/html", "utf-8");
    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else {
            finish();
        }
    }
}
