package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        //get Magnitude double value and convert to String with one decimal number
        DecimalFormat formatter = new DecimalFormat("0.0");
        String earthquakeMagnitude = formatter.format(currentEarthquake.getMagnitude());
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.text_view_magnitude);
        magnitudeTextView.setText(earthquakeMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        //get Earthquake Place
        //separate place into offset and primary location by index of word "by"
        //if data does not give an offset location we use Near the + primary

        String place = currentEarthquake.getPlace();
        String offsetLocation = getContext().getString(R.string.near_the);
        String primaryLocation = place;
        int index = place.indexOf("of");
        if (index !=-1) {
            offsetLocation = place.substring(0, index + 2);
            primaryLocation = place.substring(index + 3);
        }

        //primary Location String for first TextView
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.text_view_location_primary);
        primaryLocationTextView.setText(primaryLocation);

        //offset Location for second TextView
        TextView offsetLocationTextView = (TextView) listItemView.findViewById(R.id.text_view_location_offset);
        offsetLocationTextView.setText(offsetLocation);

        //create date Object to be formatted from Time long
        Date dateObject = new Date(currentEarthquake.getTime());

        //Date Object is formatted by formatDate method and returned Date String sent to third TextView
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.text_view_date);
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        //Date Object is formatted by formatTime method and returned Time String sent to fourth TextView
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.text_view_time);
        String formattedTime = formatTime(dateObject);
        timeTextView.setText(formattedTime);

        return listItemView;
    }

    //formats the date Object created by Time long to only display the date as String
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd,yyyy");
        return dateFormat.format(dateObject);
    }

    //formats the date Object created by Time long to only display the time as String
    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColor;
        int magnitudeInt = (int) Math.floor(magnitude);
        switch (magnitudeInt) {
            case 0:
            case 1:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            case 10:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
            default:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.white);
                break;
        }
        return magnitudeColor;
    }
}
