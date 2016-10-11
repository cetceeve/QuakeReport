package com.example.android.quakereport;

/**
 * Created by fzeih on 11.10.2016.
 */

public class Earthquake {

    //
    private String mMagnitude;

    private String mPlace;

    private String mDate;

    public Earthquake(float magnitude, String place, String date) {
        mMagnitude = Float.toString(magnitude);
        mPlace = place;
        mDate = date;
    }

    public String getMagnitude() {
        if (mMagnitude !=null) {
            return mMagnitude;
        }
        return "error";
    }

    public String getPlace() {
        return mPlace;
    }

    public String getDate() {
        return mDate;
    }
}
