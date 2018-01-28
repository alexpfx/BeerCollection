package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;

import java.util.List;

public interface DeleteBeerPresenter extends BasePresenter<DeleteBeerView>{
    void deleteBeer (String beerId);

    void deleteBeers (List<String> beers);
}
