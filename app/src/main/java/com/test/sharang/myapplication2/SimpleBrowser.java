package com.test.sharang.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sharang on 5/11/15.
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {

    WebView ourbrow;
    EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);
        ourbrow = (WebView)findViewById(R.id.wv);
        ourbrow.setWebViewClient(new OurViewClient());
        ourbrow.getSettings().setJavaScriptEnabled(true);
        ourbrow.getSettings().setLoadWithOverviewMode(true);  //for completely zoomed out(overview basically)
        ourbrow.getSettings().setUseWideViewPort(true);       //woud be constrained to its own dimensions otherwise

       try{
           ourbrow.loadUrl("http://www.google.co.in");
       }catch (Exception e)
       {
           e.printStackTrace();
       }

        Button go = (Button)findViewById(R.id.bgo);
        Button back = (Button)findViewById(R.id.bb);
        Button foward = (Button)findViewById(R.id.bf);
        Button refresh = (Button)findViewById(R.id.br);
        Button clear = (Button)findViewById(R.id.bch);
        url=(EditText)findViewById(R.id.eturl);
        go.setOnClickListener(this);
        back.setOnClickListener(this);
        foward.setOnClickListener(this);
        refresh.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {

            case R.id.bgo :
                String website= url.getText().toString();
                ourbrow.loadUrl(website);
                //for hiding the keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(),0);
                break;

            case R.id.bb :
                if(ourbrow.canGoBack()) ourbrow.goBack();
                break;

            case R.id.bf :
                if(ourbrow.canGoForward()) ourbrow.goForward();
                break;

            case R.id.br : ourbrow.reload(); break;

            case R.id.bch :ourbrow.clearHistory(); break;

        }

    }
}
