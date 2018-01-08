package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by alexandre on 04/01/18.
 */

@PerActivity
public class DeleteBeerPresenterImpl implements DeleteBeerPresenter {

    private MyCollectionInteractor interactor;
    private SchedulerProvider provider;
    private DeleteBeerView view;

    @Inject
    public DeleteBeerPresenterImpl(MyCollectionInteractor interactor, SchedulerProvider provider) {
        this.interactor = interactor;
        this.provider = provider;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void bind(DeleteBeerView view) {
        this.view = view;
    }

    @Override
    public void deleteBeer(String beerId) {
        Single single = interactor.deleteBeer(beerId);
        single.subscribeOn(provider.computation()).observeOn(provider.mainThread()).subscribe(onSuccess-> view.showBeerDeleted(beerId));

    }
}
