package com.progressbar.f1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.progressbar.R;


public class ShowWebPageActivity extends Activity {
    private WebView wv;
    private String urlStr;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.show_webpage_activity);

        Intent intent = this.getIntent();
        urlStr = intent.getStringExtra("url");
        oldURL = urlStr;
        System.out.println("url:" + urlStr);

        initProgress();

        wv = (WebView) this.findViewById(R.id.webView);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);// 设置JavaScript可用

        loadURL(urlStr);


        setClient();

    }


    private void initProgress() {
        dialog = new ProgressDialog(this);
        dialog.setMax(100);
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setTitle("温馨提示");
        dialog.setCancelable(false);
        dialog.setMessage("正在努力的为你加载中。。。");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }


    String oldURL = "";

    private void setClient() {
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, final String url) {
                System.out.println("在网页进入的URL:" + url);
                if (url.equals(oldURL)) {
                    loadURL(oldURL);
                } else {
                    loadURL(url);
                }
                oldURL = url;
                return true;
            }
        });


        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                handler.obtainMessage(1, progress, 0).sendToTarget();
                if (progress == 100) {
                }
                super.onProgressChanged(view, progress);
            }
        });

    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int progress = msg.arg1;
                    if (progress == 100) {
                        dialog.hide();
                        return;
                    }
                    dialog.setProgress(progress);
                    break;
            }
        }
    };


    private void loadURL(String uri) {
        wv.loadUrl(uri);

        dialog.show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) {
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}











