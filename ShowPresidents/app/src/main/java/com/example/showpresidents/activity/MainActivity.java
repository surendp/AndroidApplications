package com.example.showpresidents.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.showpresidents.R;
import com.example.showpresidents.service.Controller.PresidentRepository;
import com.example.showpresidents.service.model.President;
import com.example.showpresidents.service.model.PresidentDatabase;
import com.example.showpresidents.service.model.PresidentList;
import com.example.showpresidents.service.view.PresidentViewModel;
import com.example.showpresidents.service.view.PresidentViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;

public class MainActivity extends AppCompatActivity {

    // Object variables
    private ListView myListView;
    private ArrayAdapter<President> myArrayAdapter;
    private Button addNewPresident;

    private PresidentViewModel presidentViewModel;
    private PresidentViewModelFactory presidentViewModelFactory;

    private PresidentDatabase presidentDatabase;
    private PresidentRepository presidentRepository;

    // on item click listener for my list view
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent presidentDetailIntent = new Intent(MainActivity.this, PresidentDetail.class);

            President president = presidentViewModel.getPresidentList().getValue().get(i);

            presidentDetailIntent.putExtra("presidentName", president.getName());
            presidentDetailIntent.putExtra("startDate", ""+president.getStartDate());
            presidentDetailIntent.putExtra("endDate", ""+president.getEndDate());

            if(presidentDetailIntent.resolveActivity(getPackageManager()) != null){
                startActivity(presidentDetailIntent);
            }
        }
    };

    // on click listener for add button
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AddPresident.class);

            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize all the object variables
        this.initObjectVariables();

        // initialize the array adapter
        this.myArrayAdapter = new ArrayAdapter<President>(this, android.R.layout.simple_list_item_1, this.presidentViewModel.getPresidentList().getValue());

        // add on click event listener to list view
        this.myListView.setOnItemClickListener(this.onItemClickListener);

        // add on click listener to add president button
        this.addNewPresident.setOnClickListener(this.onClickListener);

        // observe the live data
        this.presidentViewModel.getPresidentList().observe(this, new Observer<List<President>>() {
            @Override
            public void onChanged(@Nullable List<President> presidents) {
                myArrayAdapter.notifyDataSetChanged();
            }
        });

        // set the adapter to the list view
        this.myListView.setAdapter(this.myArrayAdapter);
    }


    private void initObjectVariables() {
        // initialize the list view
        this.myListView = (ListView) findViewById(R.id.listView);

        // initialze button to add president
        this.addNewPresident = (Button) findViewById(R.id.buttonAddPresident);

        // initialize president database and repository
        this.presidentDatabase = PresidentDatabase.getInstance(this);

        //  initialize president repository
        this.presidentRepository = new PresidentRepository(this.presidentDatabase);

        // initialize the president view model factory
        this.presidentViewModelFactory = new PresidentViewModelFactory(this.presidentRepository);

        // create view model object
        this.presidentViewModel = ViewModelProviders.of(this, this.presidentViewModelFactory).get(PresidentViewModel.class);

    }
}
