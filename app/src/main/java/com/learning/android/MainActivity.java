package com.learning.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView myWebView = findViewById(R.id.webview);

        myWebView.loadUrl("file:///android_asset/index.html");

        myWebView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = myWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setDomStorageEnabled(true);

        myWebView.addJavascriptInterface(jsInterface, "android");
    }

    public class JsInterface {
        Context mContext;

        JsInterface(Context c) {
            mContext = c;
            Log.v("Log", "Inicializando jsInterfase ");
        }

        @JavascriptInterface
        public void runToast(String msg) {
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
        }
    }
}
