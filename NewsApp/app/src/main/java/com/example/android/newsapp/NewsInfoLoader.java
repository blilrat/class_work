package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Loads a list of newsApps by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class NewsInfoLoader extends AsyncTaskLoader<List<NewsInfo>> {

    /**
     * Tag for log messages
     */
    private static final String LOG_TAG = NewsInfoLoader.class.getName();

    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link NewsInfoLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public NewsInfoLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.e(LOG_TAG, "onstartloading start");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<NewsInfo> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        Log.e(LOG_TAG, "loadinbackground start");
        // Perform the network request, parse the response, and extract a list of newsApps.
        List<NewsInfo> newsApps = QueryUtils.fetchNewsInfoData(mUrl);
        return newsApps;
    }
}