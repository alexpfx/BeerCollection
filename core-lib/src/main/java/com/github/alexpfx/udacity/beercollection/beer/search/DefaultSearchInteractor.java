package com.github.alexpfx.udacity.beercollection.beer.search;


import com.github.alexpfx.udacity.beercollection.beer.BeerLocalDataSource;
import com.github.alexpfx.udacity.beercollection.dagger.SearchScope;
import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.local.LocalType;
import com.github.alexpfx.udacity.beercollection.util.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * Created by alexandre on 17/10/17.
 */

@SearchScope
public class DefaultSearchInteractor implements SearchInteractor {
    private BeerRemoteDataSource remote;

    private BeerLocalDataSource beerLocalDataSource;
    private SchedulerProvider schedulerProvider;
    private Consumer<? super LocalType<List<Beer>>> onNext = (Consumer<LocalType<List<Beer>>>) listLocalType ->
            beerLocalDataSource.insert(listLocalType);
    private Consumer<? super Throwable> onError = (Consumer<Throwable>) throwable -> {

    };

    @Inject
    public DefaultSearchInteractor(BeerRemoteDataSource remote, BeerLocalDataSource
            beerLocalDataSource, SchedulerProvider schedulerProvider) {
        this.remote = remote;
        this.beerLocalDataSource = beerLocalDataSource;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public Single<LocalType<List<Beer>>> searchBeers(String query) {
        Single<LocalType<List<Beer>>> cache = remote.search(query).cache();

        cache.subscribeOn(schedulerProvider.computation()).observeOn(schedulerProvider.computation()).subscribe
                (onNext, onError);

        return cache;
    }
}
