package com.test.sharang.myapplication2;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;

/**
 * Created by sharang on 4/11/15.
 */
public class Slider extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener ,SlidingDrawer.OnDrawerOpenListener, SlidingDrawer.OnDrawerCloseListener {
    SlidingDrawer sd;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding);
        Button b1= (Button)findViewById(R.id.hb1);
        Button b2= (Button)findViewById(R.id.hb2);
        Button b3= (Button)findViewById(R.id.hb3);
        Button b4= (Button)findViewById(R.id.hb4);
        sd = (SlidingDrawer)findViewById(R.id.slidingD);
        sd.setOnDrawerOpenListener(this);
        sd.setOnDrawerCloseListener(this);
       CheckBox checkbox= (CheckBox)findViewById(R.id.cbSlider);
        checkbox.setOnCheckedChangeListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    switch(v.getId())
    {
        case R.id.hb1 : sd.open();break;

        case R.id.hb2 : break;

        case R.id.hb3 : sd.toggle();break;

        case R.id.hb4 : sd.close();break;



    }



    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(buttonView.isChecked())
        {
         sd.lock();
        }
        else
        {
         sd.unlock();
        }


    }

    @Override
    public void onDrawerOpened() {

        mp = MediaPlayer.create(this,R.raw.test2);
        mp.start();

    }


    @Override
    public void onDrawerClosed() {
        mp.release();
    }
}
