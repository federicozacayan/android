package com.learning.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public final static int REQUEST_CODE = 4444;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkDrawOverlayPermission();

    }

    public void checkDrawOverlayPermission() {
        if (android.os.Build.VERSION.SDK_INT > 22) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                startService(new Intent(MainActivity.this, WindowFloatListener.class));
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && Build.VERSION.SDK_INT > 22) {
            if (Settings.canDrawOverlays(this)) {
                startService(new Intent(MainActivity.this, WindowFloatListener.class));
            }
        }
    }
}
