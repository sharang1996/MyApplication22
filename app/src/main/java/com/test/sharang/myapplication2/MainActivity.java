package com.test.sharang.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double x,y,res2=0;
    Button add1,sub1,set,add,sub,mult,divide;
    TextView display;
    EditText num1,num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add1 = (Button)findViewById(R.id.b_add1);
        sub1 = (Button)findViewById(R.id.b_sub1);
        set = (Button)findViewById(R.id.b_set);
        add = (Button)findViewById(R.id.b_add2);
        sub = (Button)findViewById(R.id.b_sub2);
        mult = (Button)findViewById(R.id.b_multiply);
        divide = (Button)findViewById(R.id.b_divide);
        display = (TextView)findViewById(R.id.tv);
        num1= (EditText)findViewById(R.id.et1);
        num2= (EditText)findViewById(R.id.et2);


        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++res2;
                display.setText("the total is"+res2);
            }
        });

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --res2;
                display.setText("the total is "+ res2);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x=Double.parseDouble(num1.getText().toString());
                y=Double.parseDouble(num2.getText().toString());
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res2=x+y;
                display.setText("the total is"+res2);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res2=x-y;
                display.setText("the total is"+res2);
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res2=x*y;
                display.setText("the total is"+res2);
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res2=x/y;
                display.setText("the total is "+res2);
            }
        });
    }
}
