package com.example.lenovo.mytvshows;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    String frag1="Popular_TvShows";
    String frag2="TopRated_TvShows";
    String frag3="FavouriteShows";
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return new PopularShows();
            case 1:
                return new TopRatedShows();
            case 2:
                return new FavShows();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return  frag1;
            case 1:
                return  frag2;
            case 2:
                return  frag3;
        }
        return super.getPageTitle(position);
    }
}
