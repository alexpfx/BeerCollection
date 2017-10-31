package com.github.alexpfx.udacity.beercollection.beer.search;

import com.github.alexpfx.udacity.beercollection.base.Repository;
import com.github.alexpfx.udacity.beercollection.domain.model.database.SearchBeerTO;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 16/10/17.
 */

public interface SearchRepository extends Repository {
    Flowable<SearchBeerTO> search (String name);
}
