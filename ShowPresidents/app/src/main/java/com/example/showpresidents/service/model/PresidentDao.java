package com.example.showpresidents.service.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by suren on 22/04/2018.
 */

@Dao
public interface PresidentDao {

    @Query("SELECT * FROM  president")
    public List<President> getAll();

    @Insert(onConflict = IGNORE)
    public void insert(President president);

    @Delete
    public void delete(President president);
}
