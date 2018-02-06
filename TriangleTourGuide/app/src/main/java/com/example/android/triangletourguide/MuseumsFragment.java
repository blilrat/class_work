package com.example.android.triangletourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link Fragment} that displays a list of phrases.
 */
public class MuseumsFragment extends Fragment {


    public MuseumsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.string.ncmuseumofart, R.string.ncmuseumofart_add, R.drawable.ncmuseumofart));
        words.add(new Word(R.string.lifeandscience, R.string.lifeandscience_add, R.drawable.lifeandscience));
        words.add(new Word(R.string.ncmuseumofhistory, R.string.ncmuseumofhistory_add, R.drawable.ncmuseumhistory));
        words.add(new Word(R.string.ncmuseumofnatscience, R.string.ncmuseumofnatsciences_add, R.drawable.ncmuseumnatscience));
        words.add(new Word(R.string.popehouse, R.string.popehouse_add, R.drawable.popehousemuseum));
        words.add(new Word(R.string.marbleskids, R.string.marbleskids_add, R.drawable.marbleskids));
        words.add(new Word(R.string.cityofraleigh, R.string.cityofraleigh_add, R.drawable.cityofraleigh));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_museums);

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
