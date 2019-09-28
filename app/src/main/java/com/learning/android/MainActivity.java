package com.learning.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This method NotificationCompat.Builder is deprecated
        NotificationCompat.Builder nb = new NotificationCompat.Builder(this);
        nb.setContentText("This is the message");
        nb.setContentTitle("This is the title");
        nb.setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(1, nb.build());
    }
}
