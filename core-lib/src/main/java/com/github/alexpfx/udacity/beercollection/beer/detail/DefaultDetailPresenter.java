package com.github.alexpfx.udacity.beercollection.beer.detail;

import com.github.alexpfx.udacity.beercollection.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by alexandre on 04/11/17.
 */

@PerActivity
public class DefaultDetailPresenter implements DetailPresenter {

    private final LoadBeerInteractor interactor;
    private final SchedulerProvider schedulerProvider;
    private DetailView view;
    private Disposable subscription;


    @Inject
    public DefaultDetailPresenter(LoadBeerInteractor interactor, SchedulerProvider schedulerProvider) {
        this.interactor = interactor;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void load(String beerId) {
        System.out.println("detailView " + view);
        subscription = interactor
                .load(beerId)
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(
                        next -> view.showBeer(next),
                        error -> {
                            error.printStackTrace();
                            System.out.println("error!");
                            view.showLoadError(error);
                        }, () -> {
                            System.out.println("onComplete ");
                        });
    }


    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    @Override
    public void bind(DetailView view) {
        this.view = view;
    }
}

//beerWrapper -> view.showBeer(beerWrapper.getData()),
//        view::showLoadError
