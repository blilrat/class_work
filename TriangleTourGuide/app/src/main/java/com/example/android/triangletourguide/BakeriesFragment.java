package com.example.android.triangletourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link Fragment} that displays a list of bakeries.
 */
public class BakeriesFragment extends Fragment {


    public BakeriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.string.boulted, R.string.boulted_add, R.drawable.boulted));
        words.add(new Word(R.string.la_farm, R.string.la_farm_add, R.drawable.le_farm));
        words.add(new Word(R.string.bittersweet, R.string.bittersweet_add, R.drawable.bittersweet));
        words.add(new Word(R.string.neomonde, R.string.neomonde_add, R.drawable.neomonde));
        words.add(new Word(R.string.the_morning_times, R.string.the_morning_times_add, R.drawable.themorningtimes));
        words.add(new Word(R.string.lucettegrace, R.string.lucettegrace_add, R.drawable.lettucegrace));
        words.add(new Word(R.string.groovy_duck, R.string.groovy_duck_add, R.drawable.groovyduck));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_bakeries);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);



        return rootView;
    }

}
