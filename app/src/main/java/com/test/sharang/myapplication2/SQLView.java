package com.test.sharang.myapplication2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by sharang on 1/8/16.
 */
public class SQLView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tv = (TextView)findViewById(R.id.tvsqlinfo);
        HotOrNot info = new HotOrNot(this);
        String data=info.getData();
        tv.setText(data);
    }
}
