package com.test.sharang.myapplication2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by sharang on 5/11/15.
 */
public class Flipper extends Activity implements View.OnClickListener {
    ViewFlipper flippy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
        flippy=(ViewFlipper)findViewById(R.id.viewFlipper);
        flippy.setOnClickListener(this);
        flippy.setFlipInterval(1000);
        flippy.startFlipping();

    }

    @Override
    public void onClick(View v) {
        flippy.stopFlipping();
        flippy.showNext();
    }
}
