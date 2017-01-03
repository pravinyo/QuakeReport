package com.is_great.pro.quakereport;

/**
 * Created by Pravinyo on 12/30/2016.
 */

public class Earthquake {

    private double mMagnitude;
    private String mPlace;
    private long mMilliSecond;
    private String mURL;

    public Earthquake(double Magnitude,String Place,long date,String url){
        mMagnitude=Magnitude;
        mPlace=Place;
        mMilliSecond=date;
        mURL=url;
    }

    public double getMagnitude(){
        return mMagnitude;
    }
    public String getPlace(){
        return mPlace;
    }
    public long getTimeInMilliSecond(){
        return mMilliSecond;
    }
    public String getURL(){
        return mURL;
    }
}
