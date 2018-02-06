package com.example.android.triangletourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * {@link Fragment} that displays a list of color vocabulary words.
 */
public class MtbTrailsFragment extends Fragment {


    public MtbTrailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.string.rtp, R.string.rtp_gps, R.drawable.rtpparking));
        words.add(new Word(R.string.crabtree_park, R.string.crabtree_park_gps, R.drawable.crabentrance));
        words.add(new Word(R.string.san_lee_gravity, R.string.san_lee_gravity_gps, R.drawable.sanlee2));
        words.add(new Word(R.string.new_light, R.string.new_light_gps, R.drawable.newlight1));
        words.add(new Word(R.string.umstead, R.string.umstead_gps, R.drawable.umstead));
        words.add(new Word(R.string.old_reedy_creek, R.string.old_reedy_creek_gps, R.drawable.umstead286));
        words.add(new Word(R.string.briar_chapel, R.string.briar_chapel_gps, R.drawable.briarchapel1));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_trails);

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
