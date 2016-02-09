package com.test.sharang.myapplication2;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by sharang on 26/10/15.
 */
public class Prefs extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
