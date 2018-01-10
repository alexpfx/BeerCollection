package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.detail.DetailPresenter;
import com.github.alexpfx.udacity.beercollection.beer.detail.DetailPresenterImpl;
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
    DetailPresenter detailPresenter(DetailPresenterImpl detailPresenterImpl) {
        return detailPresenterImpl;
    }



}
