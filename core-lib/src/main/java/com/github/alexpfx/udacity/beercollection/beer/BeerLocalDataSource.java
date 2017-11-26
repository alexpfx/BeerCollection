package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 29/10/17.
 */

public interface BeerLocalDataSource {
    void insert (List<Beer> beers);
    Flowable<Beer> load (String id);
}
