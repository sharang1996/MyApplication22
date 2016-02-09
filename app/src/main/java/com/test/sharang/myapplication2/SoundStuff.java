package com.test.sharang.myapplication2;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Created by sharang on 4/11/15.
 */

public class SoundStuff extends Activity implements View.OnClickListener ,View.OnLongClickListener{

    SoundPool sp;
int explosion=0;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        sp= new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        explosion = sp.load(this,R.raw.testsong,1);
        mp=MediaPlayer.create(this,R.raw.test2);
    }

    @Override
    public void onClick(View v) {

       if(explosion!=0) sp.play(explosion,1,1,1,0,2);
    }

    @Override
    public boolean onLongClick(View v) {
        mp.start();
        return false;
    }
}