package com.test.sharang.myapplication2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sharang on 5/11/15.
 */
public class SharedPrefs extends Activity implements View.OnClickListener {

    EditText shareddata;
    TextView dataresults;
    SharedPreferences someData;
    public  static String filename="MySharedString";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        Button save = (Button)findViewById(R.id.bsave);
        Button load = (Button)findViewById(R.id.bload);
       shareddata=(EditText)findViewById(R.id.etsharedd);
        dataresults=(TextView)findViewById(R.id.tvdresults);

        someData=getSharedPreferences(filename,0);

        save.setOnClickListener(this);
        load.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.bsave :
                String stringdata = shareddata.getText().toString();
                SharedPreferences.Editor edit = someData.edit();
                edit.putString("SharedString",stringdata);
                edit.commit();
                break;

            case R.id.bload :
                someData=getSharedPreferences(filename,0);
                String gotdata= someData.getString("SharedString","couldnt load data");
                dataresults.setText(gotdata);
                break;


        }
    }
}
