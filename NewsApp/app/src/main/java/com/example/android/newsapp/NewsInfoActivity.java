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
package com.example.android.newsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsInfoActivity extends AppCompatActivity implements LoaderCallbacks<List<NewsInfo>> {

    public static final String LOG_TAG = NewsInfoActivity.class.getName();
    private static final String THE_GUARDIAN_API = "http://content.guardianapis.com/search";
    /**
     * Constant value for the newsApp loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWS_APP_LOADER_ID = 1;
    /**
     * Adapter for the list of newsApps
     */
    private NewsInfoAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_app_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView newsAppListView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsAppListView.setEmptyView(mEmptyStateTextView);
        mProgressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        newsAppListView.setEmptyView(mProgressBar);

        // Create a new adapter that takes an empty list of newsApps as input
        mAdapter = new NewsInfoAdapter(this, new ArrayList<NewsInfo>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsAppListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected newsApp.
        newsAppListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current newsApp that was clicked on
                NewsInfo currentNewsInfo = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsAppUri = Uri.parse(currentNewsInfo.getUrl());

                // Create a new intent to view the newsApp URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsAppUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_APP_LOADER_ID, null, this);
        } else {
            // display error
            mProgressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText("No Internet Connection.");
        }

    }


    @Override
    public Loader<List<NewsInfo>> onCreateLoader(int i, Bundle bundle) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String searchString = sharedPrefs.getString(
                getString(R.string.settings_section_key),
                getString(R.string.settings_section_default));

        String apiKey = sharedPrefs.getString(
                getString(R.string.settings_key_key),
                getString(R.string.settings_key_default)
        );

        Uri baseUri = Uri.parse(THE_GUARDIAN_API);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", searchString);
        uriBuilder.appendQueryParameter("api-key", apiKey);
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        Log.e("web url: ", uriBuilder.toString());
        return new NewsInfoLoader(this, uriBuilder.toString());

    }


    @Override
    public void onLoadFinished(Loader<List<NewsInfo>> loader, List<NewsInfo> newsApps) {
        mProgressBar.setVisibility(View.GONE);
        // Set empty state text to display "No newsApps found."
        mEmptyStateTextView.setText(R.string.no_newsApps);

        Log.e(LOG_TAG, "onloadfinished start");
        // Clear the adapter of previous newsApp data
        mAdapter.clear();

        // If there is a valid list of {@link NewsInfo}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (newsApps != null && !newsApps.isEmpty()) {
            mEmptyStateTextView.setVisibility(View.GONE);
            mAdapter.addAll(newsApps);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsInfo>> loader) {
        Log.e(LOG_TAG, "onloaderreset start");
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
