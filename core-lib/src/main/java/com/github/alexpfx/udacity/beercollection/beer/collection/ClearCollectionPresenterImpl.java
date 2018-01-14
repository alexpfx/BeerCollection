package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@PerActivity
public class ClearCollectionPresenterImpl implements ClearCollectionPresenter {
    private final SchedulerProvider provider;
    private final MyCollectionInteractor interactor;
    private ClearCollectionView view;
    private CompositeDisposable compositeDisposable;

    @Inject
    public ClearCollectionPresenterImpl(MyCollectionInteractor interactor, SchedulerProvider schedulerProvider) {
        this.interactor = interactor;
        this.provider = schedulerProvider;
    }

    @Override
    public void init(ClearCollectionView view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public void unLoad() {
        compositeDisposable.dispose();
        view = null;
    }

    @Override
    public void clearCollection() {
        Disposable disposable = interactor
                .clearCollectionData()
                .subscribeOn(provider.computation())
                .observeOn(provider.mainThread())
                .subscribe(onSuccess -> view.showClearDataSuccessful(), onError -> view.showClearDataError());
        compositeDisposable.add(disposable);

    }
}
