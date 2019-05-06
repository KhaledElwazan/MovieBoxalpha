package com.example.moviebox_alpha.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.moviebox_alpha.retrofit.Result;

@Database(entities = {Result.class}, version = 1)
public abstract class MovieDB extends RoomDatabase {


    private static MovieDB movieDB;

    public static MovieDB getInstance(Context context) {
        if (null == movieDB) {
            movieDB = buildDatabaseInstance(context);
        }
        return movieDB;
    }

    private static MovieDB buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                MovieDB.class,
                Constants.DB_NAME)
                .allowMainThreadQueries().build();
    }

    public abstract MovieDAO getMovieDBDao();

    public void cleanUp() {
        movieDB = null;
    }


}