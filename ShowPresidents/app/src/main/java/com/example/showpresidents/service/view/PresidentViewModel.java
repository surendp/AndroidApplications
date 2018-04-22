package com.example.showpresidents.service.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.showpresidents.service.Controller.PresidentRepository;
import com.example.showpresidents.service.model.President;
import com.example.showpresidents.service.model.PresidentList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by suren on 22/04/2018.
 */

public class PresidentViewModel extends ViewModel {

    private PresidentRepository presidentRepository;        // repository to get the presidents list


    public PresidentViewModel(PresidentRepository presidentRepository){
        this.presidentRepository = presidentRepository;
    }

    public LiveData<List<President>> getPresidentList(){
        return this.presidentRepository.getPresidentList();
    }
}
