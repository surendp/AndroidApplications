package com.example.showpresidents.service.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suren on 22/04/2018.
 */

// contains the list of presidents
public class PresidentList {

    // contains list of presidents
    private List<President> presidentList;

    // constructor
    public PresidentList(){
        this.presidentList = new ArrayList<President>();
    }

    // getter
    public List<President> getPresidentList(){
        // if the list is empty, create the list of presidents
        if(this.presidentList.isEmpty()){
            this.createPresidents();
        }
        return this.presidentList;
    }

    // setter
    public void setPresidentList(List<President> presidentList){this.presidentList = presidentList;}

    // create list of presidents
    private void createPresidents() {
        this.presidentList.add(new President("Surendra Pandey", 2015, 2019));
    }
}
