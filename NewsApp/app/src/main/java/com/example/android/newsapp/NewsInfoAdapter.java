package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static com.example.android.newsapp.R.id.section;

/**
 * Created by Bob on 9/16/2016.
 */
public class NewsInfoAdapter extends ArrayAdapter<NewsInfo> {


    public NewsInfoAdapter(Context context, List<NewsInfo> newsApps) {
        super(context, 0, newsApps);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_app_list_item, parent, false);
        }

        // Get the {@link NewsInfo} object located at this position in the list
        NewsInfo currentNewsInfo = getItem(position);

        // Get the sectionname and place it in a view
        String sectionName = currentNewsInfo.getSection();
        TextView nameTextView = (TextView) listItemView.findViewById(section);
        nameTextView.setText(sectionName);


        // Get the title and place it in a view
        String originalTitle = currentNewsInfo.getTitle();
        TextView primaryTitleView = (TextView) listItemView.findViewById(R.id.primary_title);
        primaryTitleView.setText(originalTitle);

        // Get the title and place it in a view
        String contributor = currentNewsInfo.getContributor();
        TextView contributorView = (TextView) listItemView.findViewById(R.id.contributor);
        contributorView.setText(contributor);

        // Get the date and time and place it in a view
        String dateObject = new String(currentNewsInfo.getDateTime());
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(dateObject);


        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

}
