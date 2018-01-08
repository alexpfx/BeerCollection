package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;


public interface BeerLocalDataSource {
    void insert(List<Beer> beers);

    Flowable<Beer> load(String id);

    Single<Integer> clearCache(long elapsedTime);
}
