package com.github.alexpfx.udacity.beercollection;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies((BeerApp) getActivity().getApplication());
    }


    protected abstract void injectDependencies(BeerApp app);


    public Intent getActivityIntent() {
        return getActivity().getIntent();
    }
}
