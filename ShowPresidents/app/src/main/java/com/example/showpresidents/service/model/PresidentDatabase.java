package com.example.showpresidents.service.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

/**
 * Created by suren on 22/04/2018.
 */

@Database(entities = President.class, version = 2)
public abstract class PresidentDatabase extends RoomDatabase{

    // database name
    private static final String DB_NAME = "president-db";

    // database object variable
    private static PresidentDatabase presidentDatabase;

    // method to get the instance of the variable
    public static PresidentDatabase getInstance(Context context){
        if(presidentDatabase == null){
            presidentDatabase = create(context);
        }

        return presidentDatabase;
    }

    // create and return the instance of database
    private static PresidentDatabase create(Context context){
        return Room.databaseBuilder(
                context,
                PresidentDatabase.class,
                DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public abstract PresidentDao presidentDao();
}
