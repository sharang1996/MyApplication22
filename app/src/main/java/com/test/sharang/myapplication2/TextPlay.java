package com.test.sharang.myapplication2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by sharang on 21/10/15.
 */
public class TextPlay extends Activity implements View.OnClickListener{

    Button chkcmd;
    ToggleButton pastog;
    TextView display;
     EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        chkcmd = (Button) findViewById(R.id.bresults);
        pastog = (ToggleButton) findViewById(R.id.tbpassword);
        display = (TextView) findViewById(R.id.tvresults);
        input = (EditText) findViewById(R.id.etcommands);
        display.setText("");

        pastog.setOnClickListener(this);
        chkcmd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bresults :

                String check = input.getText().toString();
                display.setText(check);
                if (check.contentEquals("left")) {
                    display.setGravity(Gravity.LEFT);

                } else if (check.contentEquals("center")) {
                    display.setGravity(Gravity.CENTER);
                } else if (check.contentEquals("right")) {
                    display.setGravity(Gravity.RIGHT);
                } else if(check.contentEquals("blue")){
                    display.setTextColor(Color.BLUE);
                }else if(check.contentEquals("WTF")){
                    Random crazy = new Random();
                    display.setText("WTF!!!!!");
                    display.setTextSize(crazy.nextInt(75));
                    display.setTextColor(Color.rgb(crazy.nextInt(265),crazy.nextInt(265),crazy.nextInt(265)));
                    switch(crazy.nextInt(3))
                    {
                        case 0 : display.setGravity(Gravity.RIGHT);break;
                        case 1 : display.setGravity(Gravity.LEFT);break;
                        case 2 : display.setGravity(Gravity.CENTER);break;
                    }


                }else {
                    display.setText("invalid");
                    display.setGravity(Gravity.CENTER);
                    display.setTextColor(Color.WHITE);
                    display.setTextSize(40);
                }

                break;

            case R.id.tbpassword :
                if (pastog.isChecked()) {
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }

                break;


        }
    }
}
