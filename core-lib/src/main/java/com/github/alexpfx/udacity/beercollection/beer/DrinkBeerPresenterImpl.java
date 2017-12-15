package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;

import javax.inject.Inject;

/**
 * Created by alexandre on 12/11/17.
 */
@PerActivity
public class DrinkBeerPresenterImpl implements DrinkBeerPresenter {


    private final SchedulerProvider schedulerProvider;
    DrinkBeerView view;
    private DrinkBeerInteractor interactor;

    @Inject
    public DrinkBeerPresenterImpl(DrinkBeerView view, DrinkBeerInteractor interactor, SchedulerProvider
            schedulerProvider) {
        this.view = view;
        this.interactor = interactor;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void drink(DrinkBeerUpdateItem item) {
        interactor.save(item).observeOn(schedulerProvider.mainThread()).subscribeOn(schedulerProvider.computation())
                .subscribe(quantity -> {
                    view.showDrinkAdded((Integer) quantity);
                    view.refresh();
                }, error -> {
                    view.showError(error);
                });
    }

    @Override
    public void onDestroy() {


    }

    @Override
    public void bind(DrinkBeerView view) {
        this.view = view;
    }
}
