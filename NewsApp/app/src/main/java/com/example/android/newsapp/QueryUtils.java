package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving newsApp data from the guardian.
 */
public final class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Query the the guardian api dataset and return an {@link ArrayList} object to represent a single newsApp.
     */
    public static List fetchNewsInfoData(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        List newsApp = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event}
        return newsApp;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the newsApp JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link List} object by parsing out information
     * about the first newsApp from the input newsAppJSON string.
     */
    private static List extractFeatureFromJson(String newsAppJSON) {
        List<NewsInfo> newsApps = new ArrayList<>();
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsAppJSON)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(newsAppJSON);
            JSONObject response = baseJsonResponse.getJSONObject("response");
            JSONArray resultsArray = response.getJSONArray("results");

            // If there are results in the features array
            for (int i = 0; i < resultsArray.length(); i++) {
                // Extract out the first feature (which is an newsApp)
                JSONObject firstFeature = resultsArray.getJSONObject(i);

                // Extract out the sectionname, webtitle, webpublicationdate, weburl values
                String section = firstFeature.getString("sectionName");
                String title = firstFeature.getString("webTitle");
                String dateTime = firstFeature.getString("webPublicationDate");
                String url = firstFeature.getString("webUrl");
                String contributor = "None Provided";
                if (firstFeature.has("tags")) {
                    contributor = "";
                    JSONArray contributorsArray = new JSONArray(firstFeature.getString("tags"));
                    // loop through the array itemList and get the items
                    for (int x = 0; x < contributorsArray.length(); x++) {
                        JSONObject tags = contributorsArray.getJSONObject(x);
                        contributor = contributor + " - " + tags.getString("webTitle"); // item at index x
                    }
                }
                // Create a new {@link Newsapp} object with the section, title, datetime,
                // and previewlink from the JSON response.
                NewsInfo newsApp = new NewsInfo(section, title, contributor, dateTime, url);

                newsApps.add(newsApp);

            }
            return newsApps;

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the newsApp JSON results", e);
        }
        return null;
    }
}
