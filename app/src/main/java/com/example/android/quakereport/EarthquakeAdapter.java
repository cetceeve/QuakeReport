package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fzeih on 11.10.2016.
 * the adapter gets its information from the arrayList and via the Earthquake class public getterMethods
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.text_view_magnitude);
        magnitudeTextView.setText(currentEarthquake.getMagnitude());

        TextView placeTextView = (TextView) listItemView.findViewById(R.id.text_view_place);
        placeTextView.setText(currentEarthquake.getPlace());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.text_view_date);
        dateTextView.setText(currentEarthquake.getDate());

        return listItemView;
    }
}
