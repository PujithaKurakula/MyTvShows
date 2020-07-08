package com.example.lenovo.mytvshows;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * A simple {@link Fragment} subclass.
 */
public class FavShows extends Fragment {

    List<ModelClass> showModels;
    static TvViewModel viewModel;
    @BindView(R.id.mAdsView) AdView mAdView;
    @BindView(R.id.recyclerfav)
    RecyclerView rv;
    ArrayList<ModelClass> modelClasses;



    public FavShows() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fav_shows, container, false);



       ButterKnife.bind(this,view);
       AdRequest adRequestBanner = new AdRequest.Builder().build();
        mAdView.loadAd(adRequestBanner);
        modelClasses=new ArrayList<>();
        showModels = new ArrayList<>();
        viewModel = ViewModelProviders.of(this).get(TvViewModel.class);


        viewModel.getAllShowInformation().observe(this, new Observer<List<ModelClass>>() {
            @Override
            public void onChanged(@Nullable List<ModelClass> myEntities) {
                rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                rv.setAdapter(new ShowsAdapter(getContext(), (ArrayList<ModelClass>) myEntities));
            }
        });



        return view;
    }

}
