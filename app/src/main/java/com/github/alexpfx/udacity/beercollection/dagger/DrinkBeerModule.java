package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerInteractor;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerInteractorImpl;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenterImpl;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 06/12/17.
 */

@Module
public class DrinkBeerModule {

    @Provides
    @PerActivity
    DrinkBeerPresenter drinkBeerPresenter (DrinkBeerPresenterImpl drinkBeerPresenter){
        return drinkBeerPresenter;
    }

    //TODO: mover para collection.
    @Provides
    @PerActivity
    DrinkBeerInteractor drinkBeerInteractor (DrinkBeerInteractorImpl drinkBeerInteractor){
        return drinkBeerInteractor;
    }

//    @Provides
//    @PerActivity
//    public DrinkBeerView drinkBeerView (Activity activity){
//        return (DrinkBeerView) activity;
//    }


}
