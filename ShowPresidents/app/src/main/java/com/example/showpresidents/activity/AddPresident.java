package com.example.showpresidents.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.showpresidents.R;
import com.example.showpresidents.service.model.President;
import com.example.showpresidents.service.model.PresidentDatabase;

/**
 * Created by suren on 22/04/2018.
 */

public class AddPresident extends AppCompatActivity {

    // Input fields to for president information
    private EditText presidentName;
    private EditText startYear;
    private EditText endYear;

    // buttons to add and return
    private Button addButton;
    private Button returnButton;

    //database
    private PresidentDatabase presidentDatabase;

    // on click event listener for buttons
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(view.getId() == addButton.getId()) {
                // get president data
                    String name = presidentName.getText().toString();
                    int startDate = Integer.parseInt(startYear.getText().toString());
                    int endDate = Integer.parseInt(endYear.getText().toString());

                    // run add president to database method
                    if (!(name.isEmpty() && startDate <= 0 && endDate < 0)) {
                        this.addPresidentToRoom(name, startDate, endDate);
                        return;
                    }

            }

            Intent intent = new Intent(AddPresident.this, MainActivity.class);
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }

        }

        private void addPresidentToRoom(String name, int startDate, int endDate) {
            // create president and insert to the database
            President president = new President(name, startDate, endDate);
            presidentDatabase.presidentDao().insert(president);

            // clear the input fields
            this.clearFields();

        }

        private void clearFields() {
            presidentName.setText("");
            startYear.setText("");
            endYear.setText("");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_president_activity);

        // Instantiate the object variables
        this.instantiateVariables();

        //set on click listeners
        this.addButton.setOnClickListener(this.onClickListener);
        this.returnButton.setOnClickListener(this.onClickListener);

    }

    private void instantiateVariables() {

        // instantiate edit text fields
        this.presidentName = (EditText) findViewById(R.id.editTextName);
        this.startYear = (EditText) findViewById(R.id.editTextStart);
        this.endYear = (EditText) findViewById(R.id.editTextEnd);

        // instantiate buttons
        this.addButton = (Button) findViewById(R.id.buttonAdd);
        this.returnButton = (Button) findViewById(R.id.buttonReturn);

        // instantiate database
        this.presidentDatabase = PresidentDatabase.getInstance(this);
    }
}
