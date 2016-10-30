/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=3&limit=100";

    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        //set empty view if Adapter is Empty
        TextView emptyView = (TextView) findViewById(R.id.empty_view);
        if (earthquakeListView != null) {
            earthquakeListView.setEmptyView(emptyView);
        }

        //Loader initialized
        getLoaderManager().initLoader(0, null, this);
        Log.i(LOG_TAG, "Loader initialized!");

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        if (earthquakeListView != null) {
            earthquakeListView.setAdapter(mAdapter);
        } else {
            Toast.makeText(EarthquakeActivity.this, "Could not find any earthquakes to display", Toast.LENGTH_LONG).show();
        }
        //ClickListener for the Earthquake objects in the List
        if (earthquakeListView != null) {
            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //the clicked item is found by position and its url obtained
                    Earthquake earthquake = mAdapter.getItem(position);
                    if (earthquake != null) {
                        String earthquakeUrl = earthquake.getUrl();
                        //this method sends implicit intent to open a website
                        openWebPage(earthquakeUrl);
                    } else {
                        Log.e(LOG_TAG, "Not able to get earthquake object from Adapter");
                        Toast.makeText(EarthquakeActivity.this, "Sry, no URL available", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    //Create new earthquake loader for this activity
    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "Loader created!");
        return new EarthquakeLoader(EarthquakeActivity.this, USGS_REQUEST_URL);
    }

    //update UI when loader is finished
    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakeList) {
        //Clear the Adapter before updating UI with new data
        mAdapter.clear();

        if (earthquakeList != null && !earthquakeList.isEmpty()) {
            mAdapter.addAll(earthquakeList);
        }
        Log.i(LOG_TAG, "Loader finished. UI updated!");

        //Hide ProgressBar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        //set Text for empty view in case the adapter is empty
        TextView emptyView = (TextView) findViewById(R.id.empty_view);
        if (emptyView != null) {
            emptyView.setText(R.string.empty_view);
        }
    }

    //clears data on loader reset
    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        mAdapter.clear();
        Log.i(LOG_TAG, "LOADER RESET!");
    }

    //implicit intent to load a web page
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
