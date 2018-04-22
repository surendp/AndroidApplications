package com.example.myapplicationsession4exercise4_3;

import android.os.NetworkOnMainThreadException;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by suren on 10/02/2018.
 */

public class FetchInformationFromUrl {

    private String url;
    private TextView textView;

    public FetchInformationFromUrl(String url, TextView textView){
        this.url = url;
        this.textView = textView;
    }

    public void fetchContent(){
        try {

           URL url = new URL("https://" + this.url);
           URLConnection connection =  url.openConnection();

           HttpURLConnection conn = null;
           if(connection instanceof HttpURLConnection){
               conn = (HttpURLConnection) connection;
           }

            // content type of the url
            String contentType = conn.getHeaderField("Content-Type");

            // content size of the url
            String length = conn.getHeaderField("Content-Length");

            // displaying output to the textView
           this.textView.setText("Content Type: "+contentType+"\nContent Size: "+length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            this.textView.setText("Malformed URL Exception found");
        } catch (IOException e) {
            this.textView.setText("IO Exception encounterd");
        } catch (NetworkOnMainThreadException e){
            this.textView.setText("Network on main thread exception");
        }
    }

    private String getContent(Scanner in) {
        return "Hello!!";
    }

}
