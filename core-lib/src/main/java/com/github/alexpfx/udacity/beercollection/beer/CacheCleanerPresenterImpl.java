package com.github.alexpfx.udacity.beercollection.beer;


import com.github.alexpfx.udacity.beercollection.beer.beer.BeerInteractor;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import javax.inject.Inject;

@PerActivity
public class CacheCleanerPresenterImpl implements CacheCleanerPresenter {

    private CacheCleanerView view;


    private BeerInteractor interactor;
    private SchedulerProvider provider;

    @Inject
    public CacheCleanerPresenterImpl(BeerInteractor interactor, SchedulerProvider provider) {
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
        interactor.clearCache(elapsedTimeLimit).subscribe(aVoid -> view.showCacheWasCleanedUp ());
    }
}
