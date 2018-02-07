package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.beer.BeerInteractor;
import com.github.alexpfx.udacity.beercollection.beer.beer.BeerInteractorImpl;
import com.github.alexpfx.udacity.beercollection.beer.beer.LoadBeerInfoPresenter;
import com.github.alexpfx.udacity.beercollection.beer.beer.LoadBeerInfoPresenterImpl;
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


    @PerActivity
    @Provides
    LoadBeerInfoPresenter detailPresenter(LoadBeerInfoPresenterImpl detailPresenterImpl) {
        return detailPresenterImpl;
    }
}
