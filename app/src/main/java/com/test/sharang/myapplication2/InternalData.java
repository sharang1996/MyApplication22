package com.test.sharang.myapplication2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sharang on 5/11/15.
 */
public class InternalData extends Activity implements View.OnClickListener {


    EditText shareddata;
    TextView dataresults;
    FileOutputStream fos;
    String FILENAME="Internal String";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        Button save = (Button)findViewById(R.id.bsave);
        Button load = (Button)findViewById(R.id.bload);
        shareddata=(EditText)findViewById(R.id.etsharedd);
        dataresults=(TextView)findViewById(R.id.tvdresults);
        save.setOnClickListener(this);
        load.setOnClickListener(this);

        try {
            fos=openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.bsave :
                String data = shareddata.getText().toString();


                //saving data via file

                /*   File f = new File(FILENAME);
                try {
                    fos=new FileOutputStream(f);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                  */
                try {
                    fos=openFileOutput(FILENAME,Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;

            case R.id.bload :
                new  loadsomestuff().execute(FILENAME);

                break;


        }
    }

    public class loadsomestuff extends AsyncTask<String,Integer,String>{

        ProgressDialog dialog;

        protected void onPreExecute()
        {
          dialog=new ProgressDialog(InternalData.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();
        }


        @Override
        protected String doInBackground(String... params) {
            String collected=null;
            FileInputStream fis=null;
            for(int i=0;i<20;++i)
            {

                publishProgress(5);
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dialog.dismiss();
            try {
                fis= openFileInput(FILENAME);
                byte[] dataArray = new byte[fis.available()];
                while(fis.read(dataArray)!=-1)
                {
                    collected = new String(dataArray);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

     protected void onProgressUpdate(Integer...progress)
     {
             dialog.incrementProgressBy(progress[0]);
     }

        protected void onPostExecute(String Result)
        {

            dataresults.setText(Result);
        }


    }
}
