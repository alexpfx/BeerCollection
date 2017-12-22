package com.github.alexpfx.udacity.beercollection.databaselib.search;

import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.List;

import io.reactivex.Single;


public interface BeerRemoteDataSource {
    Single<List<Beer>> search(String name);
    Single<Beer> load (String id);

}
