package com.example.showpresidents.service.Controller;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.showpresidents.service.model.President;
import com.example.showpresidents.service.model.PresidentDatabase;

import java.util.List;

/**
 * Created by suren on 22/04/2018.
 */

public class GetPresidentList extends AsyncTask<Integer, List<President>, Integer> {

    private PresidentDatabase presidentDatabase;
    private MutableLiveData<List<President>> listMutableLiveData;

    public GetPresidentList(PresidentDatabase presidentDatabase, MutableLiveData<List<President>> listLiveData){
        this.presidentDatabase = presidentDatabase;
        this.listMutableLiveData = listLiveData;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        if(this.presidentDatabase.presidentDao().getAll() == null){
            this.presidentDatabase.presidentDao().insert(new President("Surendra Pander", 2000, 2005));
        }

        publishProgress(this.presidentDatabase.presidentDao().getAll());
        return null;
    }

    @Override
    protected void onProgressUpdate(List<President>... lists){
        Log.d("From background", lists[0].toString());
        this.listMutableLiveData.setValue(lists[0]);
    }

}
