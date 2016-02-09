package com.test.sharang.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sharang gupta on 23/10/15.
 */
public class Email extends Activity implements View.OnClickListener{

    Button sendmail;
    String Spemail, Sintro, Soutro, Sstupidthings, Shatefulaction, Spname;
    EditText pemail, intro, outro, stupidthings, hatefulaction, pname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        initializevars();
        sendmail.setOnClickListener(this);

    }

    private void initializevars() {
        pemail=(EditText)findViewById(R.id.editText);
        pname=(EditText)findViewById(R.id.editText2);
        intro=(EditText)findViewById(R.id.editText3);
        stupidthings=(EditText)findViewById(R.id.editText4);
        hatefulaction=(EditText)findViewById(R.id.editText5);
        outro=(EditText)findViewById(R.id.editText6);
        sendmail=(Button)findViewById(R.id.nbutton);
    }

    @Override
    public void onClick(View v) {

        etts();//edit text to string
        String emailaddress[]={Spemail};
        String message = "well hello "+Spname+"I just wanted to say " + Sintro+"Not only that but I hate when u "+Sstupidthings+"that just really makes me crazy.I just want to "+Shatefulaction+"well thats all..and "+Soutro;

        Intent sendMail = new Intent(Intent.ACTION_SEND);
        sendMail.putExtra(Intent.EXTRA_EMAIL,emailaddress);
        sendMail.putExtra(Intent.EXTRA_SUBJECT,"i hate u");
        sendMail.setType("plain/text");
        sendMail.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(sendMail);
    }

    private void etts()//edit text to string
    {
       Spname=pname.getText().toString();
        Spemail=pemail.getText().toString();
        Shatefulaction=hatefulaction.getText().toString();
        Sintro=intro.getText().toString();
        Sstupidthings=stupidthings.getText().toString();
        Soutro=outro.getText().toString();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}


