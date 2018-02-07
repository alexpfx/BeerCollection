package com.github.alexpfx.udacity.beercollection.collection.adapter;

import android.view.View;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe utilitária que permite múltiplos click listeners para uma mesma view.
 */
public class CompositeClickListener implements View.OnClickListener {

    private View view;

    private Collection<View.OnClickListener> listenerCollection = new ArrayList<>();


    public CompositeClickListener(View view) {
        this.view = view;
    }


    public void registerListener(View.OnClickListener onClickListener) {
        listenerCollection.add(onClickListener);
    }


    @Override
    public void onClick(View v) {
        for (View.OnClickListener onClickListener : listenerCollection) {
            onClickListener.onClick(view);
        }
    }
}
