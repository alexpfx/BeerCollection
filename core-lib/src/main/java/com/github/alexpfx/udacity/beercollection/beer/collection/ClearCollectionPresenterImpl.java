package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import javax.inject.Inject;

@PerActivity
public class ClearCollectionPresenterImpl implements ClearCollectionPresenter {
    private final SchedulerProvider provider;
    private final MyCollectionInteractor interactor;
    private ClearCollectionView view;

    @Inject
    public ClearCollectionPresenterImpl(MyCollectionInteractor interactor, SchedulerProvider schedulerProvider) {
        this.interactor = interactor;
        this.provider = schedulerProvider;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void bind(ClearCollectionView view) {
        this.view = view;
    }

    @Override
    public void clearCollection() {
        interactor
                .clearCollectionData()
                .subscribeOn(provider.io())
                .observeOn(provider.mainThread())
                .subscribe(onSuccess -> view.showClearDataSuccessful(), onError -> view.showClearDataError());
    }
}
