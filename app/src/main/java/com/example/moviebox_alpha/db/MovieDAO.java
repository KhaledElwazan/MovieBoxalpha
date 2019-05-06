package com.example.moviebox_alpha.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.moviebox_alpha.retrofit.Result;

import java.util.List;

@Dao
public interface MovieDAO {


    @Query("SELECT * FROM " + Constants.DB_TABLE)
    List<Result> getAll();


    @Insert
    void insert(Result movie);

    @Delete
    void delete(Result movie);

}