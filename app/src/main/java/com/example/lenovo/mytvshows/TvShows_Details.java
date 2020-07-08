package com.example.lenovo.mytvshows;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShows_Details extends AppCompatActivity {
   @BindView(R.id.backview)
    ImageView iv;
   @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.airdate)
    TextView air_date;
    @BindView(R.id.originaltitle)
    TextView orginaltitle;
    @BindView(R.id.adView)
            AdView mAdView;
    @BindView(R.id.favbutton)
    MaterialFavoriteButton materialFavoriteButton;
    String tvId;
    String name;
    static Context context;
    String[] s;
    TvViewModel myViewModel;
    List<ModelClass> showModels;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows__details);
        ButterKnife.bind(this);
        showModels=new ArrayList<>();
        myViewModel=ViewModelProviders.of(this).get(TvViewModel.class);

        context=this;
         s = getIntent().getStringArrayExtra("info");
        setTitle(s[1]);
        name=s[1];
        rating.setText(s[2]);
        info.setText(s[3]);
        air_date.setText(s[5]);
        orginaltitle.setText(s[6]);
        tvId=s[7];
        Picasso.with(this).load(getString(R.string.image_path) + s[4]).resize(700, 700).into(iv);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        String string;
        string=myViewModel.show_view(name);
        if(string!=null)
        {
            materialFavoriteButton.setFavorite(true,true);
        }
        else {
            materialFavoriteButton.setFavorite(false,true);
        }

        materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                ModelClass myEntity = new ModelClass();
                if (favorite) {
                    try {


                        myEntity.setPoster(s[0]);
                        myEntity.setName(s[1]);
                        myEntity.setPopularity(s[2]);
                        myEntity.setInfo(s[3]);
                        myEntity.setBackposter(s[4]);
                        myEntity.setOriginal_name(s[6]);
                        myEntity.setFirstdate(s[5]);

                        materialFavoriteButton.setFavorite(true);
                        myViewModel.insertdata(myEntity);
                        Toast.makeText(TvShows_Details.this, getString(R.string.fav_add), Toast.LENGTH_SHORT).show();


                    }  catch (SQLiteConstraintException e) {

                    }
                } else {
                    myEntity.setName(name);
                    myViewModel.deletedata(myEntity);
                    Toast.makeText(TvShows_Details.this, getString(R.string.fav_remove), Toast.LENGTH_SHORT).show();

                }
            }
        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.addwidget)
        {
            SharedPreferences sharedPreferences=context.getSharedPreferences(context.getPackageName(),MODE_PRIVATE);
            SharedPreferences.Editor se=sharedPreferences.edit();
            StringBuffer buffer=new StringBuffer();
            buffer.append(getString(R.string.wName)+s[1]+"\n"+getString(R.string.wDate)+"\t"+s[5]);
            se.putString(getString(R.string.widget),buffer.toString());
            se.apply();
            Intent intent=new Intent(TvShows_Details.this,
                    TVShowWidget.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            int[]  mypos=AppWidgetManager.getInstance(TvShows_Details.this).getAppWidgetIds(new ComponentName
                    (getApplicationContext(),
                    TVShowWidget.class));
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,mypos);
            sendBroadcast(intent);
            Toast.makeText(this, getString(R.string.addWidget), Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);
    }

}
