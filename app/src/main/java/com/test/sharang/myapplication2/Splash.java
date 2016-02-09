package com.test.sharang.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by sharang on 20/10/15.
 */
public class Splash extends Activity {
    MediaPlayer oursong;
    @Override
    public void onCreate(Bundle var) {
        super.onCreate(var);
        setContentView(R.layout.splash);
        oursong = MediaPlayer.create(Splash.this,R.raw.testsong);
        SharedPreferences getprefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getprefs.getBoolean("checkbox",true);
        if(music) oursong.start();
        Thread timer = new Thread(){
            public void run() {
                try {
                sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                  Intent openStartingpoint = new Intent("com.test.sharang.myapplication2.Menu");
                  startActivity(openStartingpoint);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        oursong.release();
        finish();
    }
}
