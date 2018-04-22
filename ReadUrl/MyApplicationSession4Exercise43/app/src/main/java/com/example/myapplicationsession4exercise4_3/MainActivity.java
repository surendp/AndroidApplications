package com.example.myapplicationsession4exercise4_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // edit text of activity_main xml file
    private EditText editText;

    // buttons of activity_main xml file
    private Button submit;
    private Button setting;
    private Button loadDefaultUrl;

    // TextView of activity_main xml file
    private TextView textView;

    // shared preference
    private SharedPreferences sharedPreferences;

    // on click event listener
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        private String url;

        @Override
        public void onClick(View view) {

            if(view.getId() == R.id.button) {
                this.url = MainActivity.this.editText.getText().toString();
                //this.clear(MainActivity.this.editText);

                FetchInformationInSeperateThread fetch = new FetchInformationInSeperateThread(MainActivity.this.textView, this.url);
                fetch.execute();
            }
            else if(view.getId() == R.id.settings){
                Intent activityPref = new Intent(MainActivity.this, ActivityPreference.class);

                if(activityPref.resolveActivity(getPackageManager()) != null){
                    startActivity(activityPref);
                }
            }
            else if(view.getId() == R.id.buttonLoad){
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                String url =  sharedPreferences.getString("pref_key_default_url", " ");

                FetchInformationInSeperateThread fetch = new FetchInformationInSeperateThread(MainActivity.this.textView, url);
                fetch.execute();

                MainActivity.this.editText.setText(url);
            }

        }

        private void clear(EditText editText){
            editText.setText("");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editText = (EditText) findViewById(R.id.editText);
        this.textView = (TextView) findViewById(R.id.textViewResult);


        // submit button to load the url
        this.submit = (Button) findViewById(R.id.button);

        // setting button to give the default url
        this.setting = (Button) findViewById(R.id.settings);

        // button to load default url
        this.loadDefaultUrl = (Button) findViewById(R.id.buttonLoad);

        // setting on click listener to the buttons
        this.submit.setOnClickListener(onClickListener);
        this.setting.setOnClickListener(onClickListener);
        this.loadDefaultUrl.setOnClickListener(onClickListener);

    }
}
