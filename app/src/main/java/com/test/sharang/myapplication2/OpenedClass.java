package com.test.sharang.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by sharang on 24/10/15.
 */
public class OpenedClass extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

  TextView question,send;
    Button returnData;
    RadioGroup selectionList;
    String gotBread,setText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        question=(TextView)findViewById(R.id.tvQuestion);
        send= (TextView)findViewById(R.id.tvText);
        returnData=(Button)findViewById(R.id.bReturn);
        selectionList=(RadioGroup)findViewById(R.id.rgAns);
        returnData.setOnClickListener(this);
        selectionList.setOnCheckedChangeListener(this);
        SharedPreferences getprefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String namepref = getprefs.getString("name","Sharang is.....");
        String optpref = getprefs.getString("list","4");
        if(optpref.contentEquals("1")) question.setText(namepref+" is");
       // Bundle getBasket = getIntent().getExtras();
        // gotBread=getBasket.getString("key");


    }

    @Override
    public void onClick(View v) {

        Intent person = new Intent();
        Bundle backpack=new Bundle();
        backpack.putString("answer",setText);
        person.putExtras(backpack);
        setResult(RESULT_OK,person);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId)
        {
            case R.id.rbAwesome :
                setText="probably right!!";
                break;
            case R.id.rbSmart:
                setText="definately right!!";
                break;
            case R.id.rbBoth :
                setText="spot on!!";
                break;
        }
        send.setText(setText);
    }
}
