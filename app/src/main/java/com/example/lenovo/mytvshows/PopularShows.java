package com.example.lenovo.mytvshows;


import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularShows extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<String> {

    @BindView(R.id.recyclercho)
    RecyclerView rv;
    @BindView(R.id.progresscho)
    ProgressBar pb;
    @BindString(R.string.tvshowUrl) String tvshowurl;
    ArrayList<ModelClass> models;
    public static final int Loader_ID=12;
    @BindView(R.id.adView) AdView mAdView;


    public PopularShows() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_popular_shows, container, false);
        ButterKnife.bind(this,v);
        models=new ArrayList<>();

        AdRequest adRequestBanner = new AdRequest.Builder().build();
        mAdView.loadAd(adRequestBanner);


        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity(). getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            getLoaderManager().initLoader(Loader_ID,null,this);
            connected = true;
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
            builder1.setTitle(R.string.alertBox);
            builder1.setMessage(R.string.no_interent);
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }

                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }
        return v;
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {

        return new AsyncTaskLoader<String>(getContext()) {
            @Nullable
            @Override
            public String loadInBackground() {

                try {
                    URL url = new URL(tvshowurl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Scanner scanner = new Scanner(inputStream);
                    scanner.useDelimiter("\\A");
                    if (scanner.hasNext()) {
                        return scanner.next();
                    } else {
                        return null;
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                pb.setVisibility(View.VISIBLE);
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        pb.setVisibility(View.GONE);

        if (s != null) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray(getString(R.string.results));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject results = jsonArray.getJSONObject(i);
                    String imageurl = results.getString(getString(R.string.posterpath));
                    String name=results.getString(getString(R.string.name));
                    String rating=results.getString(getString(R.string.voteAverage));
                    String overview=results.getString(getString(R.string.overview));
                    String backdrop=results.getString(getString(R.string.backdrop));
                    String originalName=results.getString(getString(R.string.oName));
                    String firstdate=results.getString(getString(R.string.date));
                    String id=results.getString(getString(R.string.id));
                    models.add(new ModelClass(imageurl,name,rating,overview,backdrop,originalName,firstdate,id));


                }

                rv.setLayoutManager(new GridLayoutManager(getContext(),2));
                rv.setAdapter(new ShowsAdapter(getActivity(),models));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

}
