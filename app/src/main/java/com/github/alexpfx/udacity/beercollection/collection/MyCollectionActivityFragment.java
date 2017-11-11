package com.github.alexpfx.udacity.beercollection.collection;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.beercollection.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyCollectionActivityFragment extends Fragment {

    public MyCollectionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_collection, container, false);
    }
}
