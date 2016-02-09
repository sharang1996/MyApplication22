package com.test.sharang.myapplication2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by sharang on 21/10/15.
 */
public class Menu extends ListActivity {

    String classes[]={"MainActivity","TextPlay","Email","Photo","Data","GFX","GFXSurface",

            "SoundStuff","Slider","Tabs","SimpleBrowser","Flipper","SharedPrefs","InternalData","ExternalData","SQLliteExample","Accelerate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String cact = classes[position];
       try{
           Class ourclass = Class.forName("com.test.sharang.myapplication2."+cact);
           Intent ourintent = new Intent(Menu.this,ourclass);
           startActivity(ourintent);
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowup = getMenuInflater();
        blowup.inflate(R.menu.cool_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.contactus :
                Intent i = new Intent("com.test.sharang.myapplication2.Contact");
                startActivity(i);
                break;

            case R.id.preferences :
                Intent p = new Intent("com.test.sharang.myapplication2.PREFS");
                startActivity(p);
                break;

            case R.id.exit :

                finish();
                break;




        }
        return false;
    }
}
