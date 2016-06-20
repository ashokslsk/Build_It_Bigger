package com.ashokslsk.joketeller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by ashok.kumar on 20/06/16.
 */
public class JokeDisplayFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_joke_display, container, false);
        ((TextView) view.findViewById(R.id.joke_frag_tv_joke)).setText(getArguments().getString(JokeDisplayingActivity.EXTRA_JOKE));
        return view;
    }
}