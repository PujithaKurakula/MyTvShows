package com.example.lenovo.mytvshows;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities =ModelClass.class,version = 2)
public abstract class TvDataBase extends RoomDatabase {

    public abstract TvShowDao showDao();

    public static TvDataBase tvDataBase;

    public static TvDataBase getTvShowDetails(Context context) {
        if (tvDataBase == null) {
            tvDataBase = Room.databaseBuilder(context, TvDataBase.class, "shows").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return tvDataBase;

    }
}