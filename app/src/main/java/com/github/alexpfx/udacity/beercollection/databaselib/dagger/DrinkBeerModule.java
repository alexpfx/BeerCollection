package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerInteractor;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerInteractorImpl;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 06/12/17.
 */

@Module(includes = ActivityModule.class)
public class DrinkBeerModule {

    @Provides
    @PerActivity
    DrinkBeerPresenter drinkBeerPresenter (DrinkBeerPresenterImpl drinkBeerPresenter){
        return drinkBeerPresenter;
    }

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
