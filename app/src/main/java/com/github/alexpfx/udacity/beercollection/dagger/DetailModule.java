package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.detail.LoadBeerInfoPresenter;
import com.github.alexpfx.udacity.beercollection.beer.detail.LoadBeerInfoPresenterImpl;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 04/11/17.
 */

@Module
public class DetailModule {


    @PerActivity
    @Provides
    LoadBeerInfoPresenter detailPresenter(LoadBeerInfoPresenterImpl detailPresenterImpl) {
        return detailPresenterImpl;
    }



}
