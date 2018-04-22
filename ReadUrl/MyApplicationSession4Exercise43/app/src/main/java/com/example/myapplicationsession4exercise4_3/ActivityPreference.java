package com.example.myapplicationsession4exercise4_3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by suren on 21/03/2018.
 */

public class ActivityPreference extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(this, R.xml.prefs, false);

        // Display the fragment
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Preference())
                .commit();
    }

}
