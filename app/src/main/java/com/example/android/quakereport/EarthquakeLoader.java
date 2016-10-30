package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Created by fzeih on 30.10.2016.
 * Custom Loader. Gets data from QueryUtils class and URL from EarthquakeActivity
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mURL;

    //Constructor
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.i(LOG_TAG, "Loader starting!");
    }

    //Connection, fetching Data and JSONParsing will be done in Background Thread
    @Override
    public List<Earthquake> loadInBackground() {
        if (mURL == null) {
            return null;
        }
        Log.i(LOG_TAG, "Starting to load in background thread");
        return QueryUtils.fetchEarthquakeData(mURL);
    }
}
