package com.github.alexpfx.udacity.beercollection.beer;


import com.github.alexpfx.udacity.beercollection.beer.detail.LoadBeerInteractor;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import javax.inject.Inject;

public class CacheCleanerPresenterImpl implements CacheCleanerPresenter {

    private CacheCleanerView view;


    private LoadBeerInteractor interactor;
    private SchedulerProvider provider;

    @Inject
    public CacheCleanerPresenterImpl(LoadBeerInteractor interactor, SchedulerProvider provider) {
        this.interactor = interactor;
        this.provider = provider;
    }

    @Override
    public void onDestroy() {


    }

    @Override
    public void bind(CacheCleanerView view) {
        this.view = view;
    }

    @Override
    public void clearCache(long elapsedTimeLimit) {
        view.showCacheCleanerStarted ();
        interactor.clearCache(elapsedTimeLimit).subscribe(number -> {
            view.showCacheWasCleanedUp ();
        });
    }
}
