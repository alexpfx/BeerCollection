package com.github.alexpfx.udacity.beercollection.collection;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Guideline;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.beercollection.BaseFragment;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.R;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment {


    @Nullable
    @BindView(R.id.guideline)
    Guideline guideline;


    public HistoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_history, container, false);
        executeOnActivityActionBar(ab -> ab.setDisplayHomeAsUpEnabled(true));
        return inflate;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    protected void injectDependencies(BeerApp app) {

    }
}
