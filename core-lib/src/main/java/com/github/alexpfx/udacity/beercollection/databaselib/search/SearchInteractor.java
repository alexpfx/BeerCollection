package com.github.alexpfx.udacity.beercollection.databaselib.search;


import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alexandre on 15/10/17.
 */

public interface SearchInteractor {
    Single<List<Beer>> searchBeers(String query);
}
