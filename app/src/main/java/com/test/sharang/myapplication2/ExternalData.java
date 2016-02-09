package com.test.sharang.myapplication2;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sharang on 5/11/15.
 */
public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener,View.OnClickListener{
    private TextView canWrite,canRead;
    private String state;
    boolean canW,canR;
    Spinner spinner;
    String paths[]={"music","pictures","downloads"};
    Button confirm,save;
    File path=null;
    File file=null;
    EditText saveFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);
        canRead=(TextView)findViewById(R.id.tvcanread);
        canWrite=(TextView)findViewById(R.id.tvcanwrite);
        canWrite.setText("false");
        canRead.setText("false");
       checkState();
        saveFile=(EditText)findViewById(R.id.etsavefile);
        confirm=(Button)findViewById(R.id.bsavefile);
        confirm.setOnClickListener(this);
        save=(Button)findViewById(R.id.bconfirmsave);
        save.setOnClickListener(this);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ExternalData.this,R.layout.support_simple_spinner_dropdown_item,paths);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void checkState() {
        state= Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){

            canWrite.setText("true");
            canRead.setText("true");
            canW=canR=true;
        }else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

            canWrite.setText("false"); canW=false;
            canRead.setText("true");   canR=true;
        }else{
            canWrite.setText("false");
            canRead.setText("false");
            canW=canR=false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(position){

            case 0:path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;

            case 1:path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;

            case 2:path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;



        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bsavefile:
              String f = saveFile.getText().toString()+".jpg";
                file=new File(path,f);
                checkState();
                if(canW==canR==true){
                    path.mkdirs();
                    try{
                        InputStream is = getResources().openRawResource(R.raw.wp2);
                        OutputStream os = new FileOutputStream(file);
                        byte data[] =new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();

                        Toast t = Toast.makeText(ExternalData.this,"file has been saved",Toast.LENGTH_SHORT);
                        t.show();
                        //update files for the user to use
                        MediaScannerConnection.scanFile(ExternalData.this,new String[] {file.toString()},null, new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                Toast t = Toast.makeText(ExternalData.this,"Scan completed",Toast.LENGTH_SHORT);
                                t.show();
                            }
                        });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                break;
            case R.id.bconfirmsave:
                save.setVisibility(View.VISIBLE);
                break;

        }
    }
}
