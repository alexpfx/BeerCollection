package com.github.alexpfx.udacity.beercollection.beer.beer;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@PerActivity
public class LoadBeerInfoPresenterImpl implements LoadBeerInfoPresenter {

    private final BeerInteractor interactor;
    private final SchedulerProvider schedulerProvider;
    private LoadBeerInfoPresenterView view;
    private CompositeDisposable compositeDisposable;


    @Inject
    public LoadBeerInfoPresenterImpl(BeerInteractor interactor, SchedulerProvider schedulerProvider) {
        this.interactor = interactor;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void load(String beerId) {
        Disposable disposable = interactor
                .load(beerId)
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(
                        next -> view.showBeerInfo(next),
                        error -> {
                            error.printStackTrace();
                            view.showLoadError(error);
                        }, () -> {
                            //onComplete
                        });

        compositeDisposable.add(disposable);
    }

    @Override
    public void init(LoadBeerInfoPresenterView view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unLoad() {
        this.view = null;
        compositeDisposable.dispose();
    }
}

