package com.test.sharang.myapplication2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sharang on 1/8/16.
 */
public class SQLliteExample extends Activity implements View.OnClickListener {

Button Sqlupdate,view,getinfo,edit,delete;
    EditText sqlname,sqlhotness,entryno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlliteexample);


        Sqlupdate=(Button)findViewById(R.id.bupdate);
        view=(Button)findViewById(R.id.bview);
        getinfo=(Button)findViewById(R.id.bgetinfo);
        edit=(Button)findViewById(R.id.bedit);
        delete=(Button)findViewById(R.id.bdelete);
        sqlname=(EditText)findViewById(R.id.etname);
        sqlhotness=(EditText)findViewById(R.id.ethotnessscale);
        entryno=(EditText)findViewById(R.id.etrowno);
        Sqlupdate.setOnClickListener(this);
        view.setOnClickListener(this);
        edit.setOnClickListener(this);
        getinfo.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

             switch(v.getId())
            {
                case R.id.bupdate:
                    boolean diditwork=true;

      try {
              String name = sqlname.getText().toString();
              String hotness = sqlhotness.getText().toString();
              HotOrNot entry = new HotOrNot(SQLliteExample.this);
              entry.open();
              entry.createEntry(name, hotness);
              entry.close();

      }catch (Exception e){

              diditwork=false;
              Dialog d = new Dialog(this);
              String  error = e.toString();
              d.setTitle("Dang it!!");
              TextView tv = new TextView(this);
              tv.setText(error);
              d.setContentView(tv);
              d.show();

      }finally {
                if(diditwork){
                  Dialog d = new Dialog(this);
                  d.setTitle("heck yeah!!");
                  TextView tv = new TextView(this);
                  tv.setText("success");
                  d.setContentView(tv);
                  d.show();

                }
      }
                    break;

                case R.id.bview:
                    Intent i = new Intent("com.test.sharang.myapplication2.SQLView");
                    startActivity(i);

                    break;



                case R.id.bgetinfo:

                try{
                    Long l = Long.parseLong(entryno.getText().toString());
                    HotOrNot hon = new HotOrNot(this);
                    hon.open();

                    sqlname.setText(hon.getname(l));
                    sqlhotness.setText(hon.gethotness(l));

                    hon.close();
            }catch (Exception e){

            Dialog d = new Dialog(this);
            String  error = e.toString();
            d.setTitle("Dang it!!");
            TextView tv = new TextView(this);
            tv.setText(error);
            d.setContentView(tv);
            d.show();

        }
                    break;



                case R.id.bedit:
                    try{
                    Long l2 = Long.parseLong(entryno.getText().toString());
                    String name = sqlname.getText().toString();
                    String hotness = sqlhotness.getText().toString();
                    HotOrNot obj = new HotOrNot(this);
                    obj.open();
                    obj.edit_entry(l2, name, hotness);
                    obj.close();
            }catch (Exception e){

            Dialog d = new Dialog(this);
            String  error = e.toString();
            d.setTitle("Dang it!!");
            TextView tv = new TextView(this);
            tv.setText(error);
            d.setContentView(tv);
            d.show();

        }
                    break;



                case R.id.bdelete:
                    try{
                    Long l3 = Long.parseLong(entryno.getText().toString());
                    HotOrNot obj3 = new HotOrNot(this);
                    obj3.open();
                    obj3.delete(l3);
                    obj3.close();
            }catch (Exception e){

            Dialog d = new Dialog(this);
            String  error = e.toString();
            d.setTitle("Dang it!!");
            TextView tv = new TextView(this);
            tv.setText(error);
            d.setContentView(tv);
            d.show();

        }
                    break;



            }
    }
}
