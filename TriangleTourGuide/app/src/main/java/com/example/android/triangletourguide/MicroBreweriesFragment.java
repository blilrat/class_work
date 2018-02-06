package com.example.android.triangletourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link Fragment} that displays a list of family vocabulary words.
 */
public class MicroBreweriesFragment extends Fragment {


    public MicroBreweriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.string.aviator, R.string.aviator_add, R.drawable.aviator));
        words.add(new Word(R.string.fortnight, R.string.fortnight_add, R.drawable.fortnight));
        words.add(new Word(R.string.bombshell, R.string.bombshell_add, R.drawable.bombshell));
        words.add(new Word(R.string.crankarm, R.string.crankarm_add, R.drawable.crankarm));
        words.add(new Word(R.string.neuseriver, R.string.neuseriver_add, R.drawable.neuseriver));
        words.add(new Word(R.string.brueprint, R.string.brueprint_add, R.drawable.brueprint));
        words.add(new Word(R.string.lonerider, R.string.lonerider_add, R.drawable.lonerider));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_breweries);

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
