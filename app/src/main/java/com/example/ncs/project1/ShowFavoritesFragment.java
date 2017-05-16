package com.example.ncs.project1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by NCS on 2017-05-16.
 */

public class ShowFavoritesFragment extends android.support.v4.app.Fragment {

    private static ShowFavoritesFragment instance = new ShowFavoritesFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_favorites, container, false);
        return v;
    }

    public static synchronized ShowFavoritesFragment getInstance() {
        return instance;
    }
}
