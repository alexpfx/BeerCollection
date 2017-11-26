package com.github.alexpfx.udacity.beercollection;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by alexandre on 11/11/17.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies((BeerApp) getActivity().getApplication());
    }

    protected abstract void injectDependencies(BeerApp app);

}
