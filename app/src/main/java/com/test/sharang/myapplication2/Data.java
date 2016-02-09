package com.test.sharang.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sharang on 24/10/15.
 */
public class Data extends Activity implements View.OnClickListener{
    EditText ets;
    Button b1,b2;
    TextView d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);

        d=(TextView)findViewById(R.id.tvGot);
        ets=(EditText)findViewById(R.id.etSend);
        b1= (Button)findViewById(R.id.bsa);
        b2=(Button)findViewById(R.id.bsafr);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.bsa :

                String bread= ets.getText().toString();
                Bundle basket=new Bundle();
                basket.putString("key",bread);
                Intent a= new Intent(Data.this,OpenedClass.class);
                a.putExtras(basket);
                startActivity(a);

                break;

            case R.id.bsafr :
                Intent i = new Intent(Data.this,OpenedClass.class);
                startActivityForResult(i,0);
                break;



        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            Bundle basket= data.getExtras();
            String s = basket.getString("answer");
            d.setText(s);
        }



    }
}
