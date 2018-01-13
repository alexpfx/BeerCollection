package com.github.alexpfx.udacity.beercollection.beer.detail;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by alexandre on 04/11/17.
 */

@PerActivity
public class LoadBeerInfoPresenterImpl implements LoadBeerInfoPresenter {

    private final BeerInteractor interactor;
    private final SchedulerProvider schedulerProvider;
    private LoadBeerInfoPresenterView view;
    private Disposable subscription;


    @Inject
    public LoadBeerInfoPresenterImpl(BeerInteractor interactor, SchedulerProvider schedulerProvider) {
        this.interactor = interactor;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void load(String beerId) {
        subscription = interactor
                .load(beerId)
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(
                        next -> view.showBeerInfo(next),
                        error -> {
                            error.printStackTrace();
                            view.showLoadError(error);
                        }, () -> {
                            //TODO
                        });
    }


    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
        this.view = null;
    }

    @Override
    public void bind(LoadBeerInfoPresenterView view) {
        this.view = view;
    }
}

