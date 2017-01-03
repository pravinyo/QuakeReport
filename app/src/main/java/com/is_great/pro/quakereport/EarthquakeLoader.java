package com.is_great.pro.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Pravinyo on 12/31/2016.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private final static String LOG_TAG=EarthquakeLoader.class.getName();
    private String mURL;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        // TODO: Finish implementing this constructor
        mURL=url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"In onstartLoading method");
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        // TODO: Implement this method
        Log.i(LOG_TAG,"In loadInBackground method");
        if(mURL==null){
            return null;
        }
        List<Earthquake> result = QueryUtils.fetchEarthquakeData(mURL);
        return result;
    }
}
