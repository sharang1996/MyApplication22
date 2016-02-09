package com.test.sharang.myapplication2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by sharang on 4/11/15.
 */
public class Tabs extends Activity implements View.OnClickListener {

    TabHost th;
    TextView showresults;
    long start=0,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        th = (TabHost)findViewById(R.id.tabHost);

        Button newtab= (Button)findViewById(R.id.baddtab);
        Button bstart= (Button)findViewById(R.id.bstartwatch);
        Button bstop= (Button)findViewById(R.id.bstopwatch);
        showresults=(TextView)findViewById(R.id.tvshowres);

        newtab.setOnClickListener(this);
        bstart.setOnClickListener(this);
        bstop.setOnClickListener(this);



        th.setup();
        TabHost.TabSpec specs = th.newTabSpec("tag 1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Stopwatch");
        th.addTab(specs);

        specs = th.newTabSpec("tag 2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);

        specs = th.newTabSpec("tag 3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add tab");
        th.addTab(specs);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.baddtab :
                TabHost.TabSpec ourspeck= th.newTabSpec("new tab");
                ourspeck.setContent(new TabHost.TabContentFactory() {        //could have referenced a layout as well!!!
                                                                             //like this :  setContentView(R.layout.splash); i guess....
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("You have created a new tab!!");
                        text.setTextColor(Color.CYAN);
                        text.setTextSize(60);
                        text.setGravity(Gravity.CENTER);
                        return text;
                    }
                });
                ourspeck.setIndicator("New");
                th.addTab(ourspeck);

                break;


            case R.id.bstartwatch :
                start = System.currentTimeMillis();
                break;


            case R.id.bstopwatch :
                stop=System.currentTimeMillis();

                if(start!=0)
                {
                   long res = stop-start;

                    showresults.setText(String.format("%d:%02d:%02d",(int)res/60000,((int)res/1000)%60,(int)res%1000));

                }
                break;




        }

    }
}
