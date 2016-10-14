package com.example.android.quakereport;

/**
 * Created by fzeih on 11.10.2016.
 * this simple class creates Earthquake objects
 * it has three states that are the Earthquake magnitude, place and date all as Strings
 * the states can be accessed by three public methods and will return their values
 */

public class Earthquake {

    private String mMagnitude;

    private String mPlace;

    private String mDate;

    public Earthquake(String magnitude, String place, String date) {
        mMagnitude = magnitude;
        mPlace = place;
        mDate = date;
    }

    public String getMagnitude() {
            return mMagnitude;
    }

    public String getPlace() {
        return mPlace;
    }

    public String getDate() {
        return mDate;
    }
}
