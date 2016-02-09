package com.test.sharang.myapplication2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by sharang on 1/17/16.
 */
public class HttpEx extends Activity {

    TextView httpstuff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpex);
        httpstuff=(TextView)findViewById(R.id.tvhttp);
        GetMethodEx test = new GetMethodEx();
        String returned;
       try {
           returned = test.gtInternetData();
           httpstuff.setText(returned);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
