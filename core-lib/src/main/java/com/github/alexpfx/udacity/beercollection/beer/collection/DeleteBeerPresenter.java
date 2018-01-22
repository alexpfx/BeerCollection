package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;

import java.util.Set;

/**
 * Created by alexandre on 04/01/18.
 */

public interface DeleteBeerPresenter extends BasePresenter<DeleteBeerView>{
    void deleteBeer (String beerId);

    void deleteBeers (Set<String> beers);
}
