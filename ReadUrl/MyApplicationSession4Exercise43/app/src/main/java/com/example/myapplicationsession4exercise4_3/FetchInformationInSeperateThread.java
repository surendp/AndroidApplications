package com.example.myapplicationsession4exercise4_3;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by suren on 28/02/2018.
 */

public class FetchInformationInSeperateThread extends AsyncTask<String, String, String> {

    private TextView textView;
    private String url;

    public  FetchInformationInSeperateThread(TextView textView, String url){
        this.textView = textView;
        this.url = url;
    }

    @Override
    protected String doInBackground(String... strings) {
        new FetchInformationFromUrl(this.url, this.textView).fetchContent();
        return null;
    }
}
