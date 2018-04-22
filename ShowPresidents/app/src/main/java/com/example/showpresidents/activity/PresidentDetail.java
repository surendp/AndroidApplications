package com.example.showpresidents.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.showpresidents.R;

/**
 * Created by suren on 15/02/2018.
 */

public class PresidentDetail extends AppCompatActivity {

    private TextView textView;
    private Button button;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PresidentDetail.this, MainActivity.class);

            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }
    };

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        String details = getContent("presidentName")
                        +"Start: "+getContent("startDate")
                        +"End: "+getContent("endDate");

        this.textView = (TextView) findViewById(R.id.textView);
        this.textView.setText(details);

        this.button = (Button) findViewById(R.id.button);
        this.button.setOnClickListener(this.onClickListener);
    }

    private String getContent(String key){
        return  getIntent().getExtras().getString(key)+"\n";
    }
}
