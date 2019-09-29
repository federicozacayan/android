package com.learning.android;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


public class WindowFloatListener extends Service {

    private WindowManager wm;
    private LinearLayout ll;
    private WebView myWebView;
    private String TAG = " JavaScript on background";
    private MediaPlayer m = new MediaPlayer();

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);


        myWebView = new WebView(this);
        myWebView.loadUrl("file:///android_asset/service.html");
        myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        myWebView.addJavascriptInterface(new WindowFloatListener.JsInferface(this), "android");
        int LAYOUT_FLAG = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                : WindowManager.LayoutParams.TYPE_PHONE;

        //A window of 1 pixel on the top left of the screen
        WindowManager.LayoutParams parameters =  new WindowManager.LayoutParams(
                1,
                1,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        parameters.x = 0;
        parameters.y = 0;
        parameters.gravity = Gravity.LEFT | Gravity.TOP;
        wm.addView(myWebView, parameters);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class JsInferface {
        Context mContext;

        JsInferface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void clock(String time) {
            Log.d(TAG, "service: "+time);

        }
    }




}

