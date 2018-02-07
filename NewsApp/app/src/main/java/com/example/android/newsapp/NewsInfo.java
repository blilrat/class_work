package com.example.android.newsapp;

/**
 * Created by Bob on 9/16/2016.
 */
public class NewsInfo {

    private String mSectionName;
    private String mTitle;
    private String mContributor;
    private String mDateTime;
    private String mUrl;

    /**
     * Constructs a new {@link NewsInfo} object.
     *
     * @param sectionName is the sectionName of the article
     * @param title       is the title of the article
     * @param contributor is the contributor
     * @param dateTime    is the dateTime when the article was written
     * @param url         is the website URL to find more details about the article
     */
    public NewsInfo(String sectionName, String title, String contributor, String dateTime, String url) {
        mSectionName = sectionName;
        mTitle = title;
        mContributor = contributor;
        mDateTime = dateTime;
        mUrl = url;
    }


    public String getSection() {
        return mSectionName;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContributor() {
        return mContributor;
    }

    public String getDateTime() {
        return mDateTime;
    }

    /**
     * Returns the website URL to find more information about the newsApp.
     */
    public String getUrl() {
        return mUrl;
    }

}
