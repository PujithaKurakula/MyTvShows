package com.example.lenovo.mytvshows;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "TvShows")
public class ModelClass
{

    String poster;
    @PrimaryKey @NonNull
    String name;
    String popularity;
    String info;
    String backposter;
    String original_name;
    String  firstdate;
    String id;

    public ModelClass() {
    }

    public ModelClass(String poster, String name, String popularity,
                      String info, String backposter, String original_name,
                      String firstdate, String id) {
        this.poster = poster;
        this.name = name;
        this.popularity = popularity;
        this.info = info;
        this.backposter = backposter;
        this.original_name = original_name;
        this.firstdate = firstdate;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getBackposter() {
        return backposter;
    }

    public void setBackposter(String backposter) {
        this.backposter = backposter;
    }

    public String getFirstdate() {
        return firstdate;
    }

    public void setFirstdate(String firstdate) {
        this.firstdate = firstdate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
