package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerInteractor;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerInteractorImpl;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenterImpl;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class DrinkBeerModule {

    @Provides
    @PerActivity
    DrinkBeerPresenter drinkBeerPresenter(DrinkBeerPresenterImpl drinkBeerPresenter) {
        return drinkBeerPresenter;
    }


    @Provides
    @PerActivity
    DrinkBeerInteractor drinkBeerInteractor(DrinkBeerInteractorImpl drinkBeerInteractor) {
        return drinkBeerInteractor;
    }
}
