package com.example.android.quakereport;

/**
 * Created by fzeih on 11.10.2016.
 * this simple class creates Earthquake objects
 * it has four states that are the Earthquake magnitude, place, date and specific url
 * the states can be accessed by four public methods and will return their values
 */

public class Earthquake {

    private double mMagnitude;

    private String mPlace;

    private long mTime;

    private String mUrl;

    public Earthquake(double magnitude, String place, long time, String url) {
        mMagnitude = magnitude;
        mPlace = place;
        mTime = time;
        mUrl = url;
    }

    public double getMagnitude() {
            return mMagnitude;
    }

    public String getPlace() {
        return mPlace;
    }

    public long getTime() {
        return mTime;
    }

    public String getUrl() { return mUrl; }
}
