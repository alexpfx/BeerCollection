package com.github.alexpfx.udacity.beercollection.beer.search;


import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.local.LocalType;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alexandre on 15/10/17.
 */

public interface SearchInteractor {
    Single<LocalType<List<Beer>>> searchBeers(String query);
}
