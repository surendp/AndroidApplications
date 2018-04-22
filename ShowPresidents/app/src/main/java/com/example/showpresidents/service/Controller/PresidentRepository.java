package com.example.showpresidents.service.Controller;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.util.Log;

import com.example.showpresidents.service.model.President;
import com.example.showpresidents.service.model.PresidentDatabase;
import com.example.showpresidents.service.model.PresidentList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by suren on 22/04/2018.
 */

@Singleton
public class PresidentRepository{

    private PresidentDatabase presidentDatabase;  // reference to the president list object
    private MutableLiveData<List<President>> presidentList;

    public PresidentRepository(PresidentDatabase presidentDatabase){
        this.presidentDatabase = presidentDatabase;
        this.presidentList = new MutableLiveData<List<President>>() {};
        this.presidentList.setValue(new ArrayList<President>());
    }

    public LiveData<List<President>> getPresidentList(){

        if(this.presidentDatabase.presidentDao().getAll() == null || this.presidentDatabase.presidentDao().getAll().isEmpty()){
            this.presidentDatabase.presidentDao().insert(new President("Surendra Pandey", 2000, 2005));

            // load the database with other presidents
            this.loadPresidents();
        }

        this.presidentList.setValue(this.presidentDatabase.presidentDao().getAll());

        return this.presidentList;
    }

    // load dummy presidents if the database is empty
    private void loadPresidents() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                presidentDatabase.presidentDao().insert(new President("Subodh Dahal", 2005, 2010));
                presidentDatabase.presidentDao().insert(new President("Handry", 2010, 2015));
                presidentDatabase.presidentDao().insert(new President("Christoffer", 1990, 1995));
                presidentDatabase.presidentDao().insert(new President("Liivi", 1995, 2000));
                presidentDatabase.presidentDao().insert(new President("Sauli", 1980, 1985));
                presidentDatabase.presidentDao().insert(new President("X-Men", 1985, 1990));
            }
        }).start();

    }

}
