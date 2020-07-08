package com.example.lenovo.mytvshows;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TvShowDao
{
        @Insert
        public void Show_Insert(ModelClass myEntity);
        @Update
        public void getShowDetail(ModelClass myEntity);
        @Query("select * from  TvShows")
        public LiveData<List<ModelClass>> getShowInfoAll();
        @Delete
        public void showDeleteInfo(ModelClass myEntity);
        @Query("select name from TvShows where name=:tvShow")
        public String readTvShow(String tvShow);


}
