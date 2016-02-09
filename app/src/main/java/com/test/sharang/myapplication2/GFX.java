package com.test.sharang.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by sharang on 30/10/15.
 */
public class GFX extends Activity {

    MyBringBack ourview;
    PowerManager.WakeLock WL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PowerManager PM = (PowerManager)getSystemService(Context.POWER_SERVICE);
        WL = PM.newWakeLock(PowerManager.FULL_WAKE_LOCK,"wakelock");
        super.onCreate(savedInstanceState);
        WL.acquire();
        ourview=new MyBringBack(this);
        setContentView(ourview);
    }

    @Override
    protected void onPause() {
        super.onPause();
        WL.release();
    }
}
