package com.github.alexpfx.udacity.beercollection.beer;


import com.github.alexpfx.udacity.beercollection.beer.beer.BeerInteractor;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@PerActivity
public class CacheCleanerPresenterImpl implements CacheCleanerPresenter {

    private CacheCleanerView view;


    private BeerInteractor interactor;
    private SchedulerProvider provider;
    private CompositeDisposable compositeDisposable;

    @Inject
    public CacheCleanerPresenterImpl(BeerInteractor interactor, SchedulerProvider provider) {
        this.interactor = interactor;
        this.provider = provider;
    }

    @Override
    public void init(CacheCleanerView view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unLoad() {
        this.view = null;
        compositeDisposable.dispose();

    }

    @Override
    public void clearCache(long elapsedTimeLimit) {
        view.whenClearCacheRoutineStarts();
        Disposable disposable = interactor.clearCache(elapsedTimeLimit).subscribe(count -> view.whenClearCacheRoutineEnds
                (count));

        compositeDisposable.add(disposable);


    }
}
