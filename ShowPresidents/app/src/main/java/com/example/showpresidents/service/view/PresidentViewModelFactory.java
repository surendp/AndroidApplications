package com.example.showpresidents.service.view;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.showpresidents.service.Controller.PresidentRepository;

import javax.inject.Inject;

/**
 * Created by suren on 22/04/2018.
 */

public class PresidentViewModelFactory implements ViewModelProvider.Factory{

    private PresidentRepository presidentRepository;

    @Inject
    public PresidentViewModelFactory(PresidentRepository presidentRepository){
        this.presidentRepository = presidentRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PresidentViewModel.class)) {
            return (T) new PresidentViewModel(this.presidentRepository);
        }
        else{
            throw new IllegalArgumentException("View Model not found");
        }
    }
}
