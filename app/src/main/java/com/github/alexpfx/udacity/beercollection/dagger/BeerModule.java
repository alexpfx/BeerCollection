package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.detail.BeerInteractor;
import com.github.alexpfx.udacity.beercollection.beer.detail.BeerInteractorImpl;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class BeerModule {

    @PerActivity
    @Provides
    BeerInteractor beerInteractor(BeerInteractorImpl beerInteractor) {
        return beerInteractor;
    }



}
