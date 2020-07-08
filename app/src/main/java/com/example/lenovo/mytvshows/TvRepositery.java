package com.example.lenovo.mytvshows;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class TvRepositery {


    public static TvShowDao myDao;
    LiveData<List<ModelClass>> getalldata;

    public TvRepositery(Context context){
        TvDataBase myDataBase=TvDataBase.getTvShowDetails(context);
        myDao=myDataBase.showDao();
        getalldata=myDao.getShowInfoAll();


    }
    LiveData<List<ModelClass>> getallShows(){
        return getalldata;
    }



    public void insert(ModelClass myEntity){
        new InsertTask().execute(myEntity);
    }

    public class InsertTask extends AsyncTask<ModelClass,Void,Void> {

        @Override
        protected Void doInBackground(ModelClass... myEntities) {
            myDao.Show_Insert(myEntities[0]);
            return null;
        }
    }
    public void update(ModelClass myEntity){
        new updateTask(myDao).execute(myEntity);
    }
    public class updateTask extends AsyncTask<ModelClass,Void,Void>{
        private TvShowDao dao;
        public updateTask(TvShowDao mydao){
            dao=mydao;
        }

        @Override
        protected Void doInBackground(ModelClass... myEntities) {
            myDao.getShowDetail(myEntities[0]);
            return null;
        }
    }
    public void delete(ModelClass myEntity){
        new deleteTask().execute(myEntity);
    }
    public class deleteTask extends AsyncTask<ModelClass,Void,Void> {

        @Override
        protected Void doInBackground(ModelClass... myEntities) {
            myDao.showDeleteInfo(myEntities[0]);
            return null;
        }
    }
    public String fav_shows(String shows)
    {
        return myDao.readTvShow(shows);

    }

}
