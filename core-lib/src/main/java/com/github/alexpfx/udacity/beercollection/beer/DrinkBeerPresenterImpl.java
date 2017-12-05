package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;

import javax.inject.Inject;

/**
 * Created by alexandre on 12/11/17.
 */
@PerActivity
public class DrinkBeerPresenterImpl implements DrinkBeerPresenter {


    private DrinkBeerView view;


    private DrinkBeerInteractor interactor;

    @Inject
    public DrinkBeerPresenterImpl(DrinkBeerView view, DrinkBeerInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void drink(DrinkBeerUpdateItem item) {
        interactor.save(item);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void bind(DrinkBeerView view) {
        this.view = view;
    }
}
