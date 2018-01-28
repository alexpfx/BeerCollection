package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@PerActivity
public class DrinkBeerPresenterImpl implements DrinkBeerPresenter {


    private final SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable;
    private DrinkBeerView view;
    private DrinkBeerInteractor interactor;


    @Inject
    public DrinkBeerPresenterImpl(DrinkBeerInteractor interactor, SchedulerProvider
            schedulerProvider) {
        this.view = DrinkBeerPresenter.EMPTY;
        this.interactor = interactor;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void drink(DrinkBeerUpdateItem item) {

        Disposable disposable = interactor.save(item).observeOn(schedulerProvider.mainThread()).subscribeOn
                (schedulerProvider.computation())
                .subscribe(quantity -> {
                    view.showDrinkAdded(item.getBeerId(), (Integer) quantity);
                }, error -> view.showErrorOnDrinkBeer(error));
        compositeDisposable.add(disposable);
    }

    @Override
    public void init(DrinkBeerView view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unLoad() {
        compositeDisposable.dispose();
        this.view = null;
    }
}
