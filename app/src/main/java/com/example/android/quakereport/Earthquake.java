package com.example.android.quakereport;

/**
 * Created by fzeih on 11.10.2016.
 * this simple class creates Earthquake objects
 * it has three states that are the Earthquake magnitude, place and date
 * the states can be accessed by three public methods and will return their values
 */

public class Earthquake {

    private String mMagnitude;

    private String mPlace;

    private long mTime;

    public Earthquake(String magnitude, String place, long time) {
        mMagnitude = magnitude;
        mPlace = place;
        mTime = time;
    }

    public String getMagnitude() {
            return mMagnitude;
    }

    public String getPlace() {
        return mPlace;
    }

    public long getTime() {
        return mTime;
    }
}
