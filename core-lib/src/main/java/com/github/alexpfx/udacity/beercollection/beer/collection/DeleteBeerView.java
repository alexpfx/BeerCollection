package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.base.BaseView;


public interface DeleteBeerView extends BaseView {
    void showBeerDeleted(String beerId);
    void showBeersDeleted ();

    void showCouldNotDeleteBeer();

}
