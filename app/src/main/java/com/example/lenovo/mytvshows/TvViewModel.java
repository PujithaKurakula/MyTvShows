package com.example.lenovo.mytvshows;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class TvViewModel extends AndroidViewModel {


    public TvRepositery tvRepository;
    public LiveData<List<ModelClass>> getShowData;


    public TvViewModel(@NonNull Application application) {
        super(application);
        tvRepository=new TvRepositery(application);
        getShowData=tvRepository.getallShows();
    }


    public void insertdata(ModelClass showModel){
        tvRepository.insert(showModel);
    }
    public void deletedata(ModelClass showModel){
        tvRepository.delete(showModel);
    }
    public void updatedata(ModelClass showModel){
        tvRepository.update(showModel);
    }

    public String show_view(String shows_tv){
        return tvRepository.fav_shows(shows_tv);
    }

    public LiveData<List<ModelClass>> getAllShowInformation(){
        return getShowData;
    }

}
