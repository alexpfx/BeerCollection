package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.base.BaseView;

/**
 * Created by alexandre on 04/01/18.
 */

public interface DeleteBeerView extends BaseView {
    void beerDeleted();

    void couldNotDeleteBeer();

}
