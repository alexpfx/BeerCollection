package com.github.alexpfx.udacity.beercollection.beer.search;

import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alexandre on 16/10/17.
 */

public interface BeerRemoteDataSource {
    Single<List<Beer>> search(String name);
    Single<Beer> load (String id);

}
