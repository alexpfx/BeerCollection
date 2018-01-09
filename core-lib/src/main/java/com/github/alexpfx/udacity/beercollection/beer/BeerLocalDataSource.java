package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.List;

import io.reactivex.FlowableEmitter;
import io.reactivex.Maybe;
import io.reactivex.Single;


public interface BeerLocalDataSource {
    void insert(List<Beer> beers);

    void insert(Beer beer);


    void load(String id, FlowableEmitter<Beer> emitter);

    Maybe<Beer> load(String id);

    Single<Void> clearCache(long elapsedTime);
}
