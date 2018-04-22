package com.example.myapplicationsession4exercise4_3;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * Created by suren on 06/03/2018.
 */

public class Preference extends PreferenceFragment {


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // load the preference xml file
        addPreferencesFromResource(R.xml.prefs);
    }

}
