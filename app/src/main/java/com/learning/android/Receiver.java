package com.learning.android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

@SuppressLint("ParcelCreator")
public class Receiver extends ResultReceiver {

    private MainActivity.Message message;

    public Receiver(MainActivity.Message message) {
        super(new Handler());
        this.message = message;
    }

       @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
//        super.onReceiveResult(resultCode, resultData);
        message.displayMessage(resultCode, resultData);
    }
}
