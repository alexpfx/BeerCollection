package com.github.alexpfx.udacity.beercollection;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.github.alexpfx.udacity.beercollection.utils.Consumer;


public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        injectDependencies((BeerApp) getActivity().getApplication());
    }

    protected abstract void injectDependencies(BeerApp app);

    public void executeOnActivityActionBar(Consumer<ActionBar> consumer){
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null){
            consumer.accept(supportActionBar);
        }

    }

    public Intent getActivityIntent(){
        return getActivity().getIntent();
    }
}
