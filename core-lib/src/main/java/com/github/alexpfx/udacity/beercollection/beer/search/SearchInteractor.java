package com.github.alexpfx.udacity.beercollection.beer.search;

import com.github.alexpfx.udacity.beercollection.domain.model.network.SearchBeerResponse;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 15/10/17.
 */

public interface SearchInteractor {

    Flowable<SearchBeerResponse> searchBeers (String key, String name, String order, String sort);
}
