package com.example.showpresidents.service.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by suren on 22/04/2018.
 */

@Entity
public class President implements Comparable<President> {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    private String name;
    private int startDate;
    private int endDate;

    public President(String name, int startDate, int endDate){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setStartDate(int startDate){
        this.startDate = startDate;
    }

    public void setEndDate(int endDate){
        this.endDate = endDate;
    }

    public void setID(int ID){this.ID = ID;}

    public String getName(){
        return this.name;
    }

    public int getStartDate(){
        return this.startDate;
    }

    public int getEndDate(){
        return this.endDate;
    }

    public int getID(){return this.ID;}

    @Override
    public int compareTo(@NonNull President president) {
        return this.startDate - president.getStartDate();
    }

    public String toString(){
        return this.name;
    }
}
