package com.learning.android;

import android.app.IntentService;
import android.content.Intent;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;


public class TimerService extends IntentService {
    private static String TAG = "TimerService";
    public TimerService(){
        super("Timer Service");
        setIntentRedelivery(false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("timer", "Timer service has started");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d(TAG, "onStartCommand");
        return START_STICKY;
    }


    @Override
    protected void onHandleIntent( @Nullable Intent intent) {
        if (intent == null) {
            int time = 5;
            for (int i = 0; i < time ; i++){
                Log.v("timer", "i (intent is null) = " + i);
                try{
                    SystemClock.sleep(1000);
                } catch (Exception e){

                }
            }
            Log.v("timer", "finish");
            stopSelf();
            return;
        }

        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        int time = intent.getIntExtra("time",0);
        for (int i = 0; i < time ; i++){
            Log.v("timer", "i (intent is not null) = " + i);
            try{
                Thread.sleep(1000);
            } catch (Exception e){

            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("message", "Counting done!");
        receiver.send(1234, bundle);
        stopSelf();
        return;
    }
}
