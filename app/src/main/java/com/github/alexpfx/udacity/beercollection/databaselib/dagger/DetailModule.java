package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.beer.detail.DefaultDetailPresenter;
import com.github.alexpfx.udacity.beercollection.beer.detail.DefaultLoadBeerInteractor;
import com.github.alexpfx.udacity.beercollection.beer.detail.DetailPresenter;
import com.github.alexpfx.udacity.beercollection.beer.detail.DetailView;
import com.github.alexpfx.udacity.beercollection.beer.detail.LoadBeerInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 04/11/17.
 */

@Module
public class DetailModule {

    DetailView detailView;

    public DetailModule(DetailView detailView) {
        this.detailView = detailView;
    }

    @PerActivity
    @Provides
    public DetailView detailView(){
        return detailView;
    }

    @PerActivity
    @Provides
    DetailPresenter detailPresenter(DefaultDetailPresenter defaultDetailPresenter) {
        defaultDetailPresenter.bind(detailView);
        return defaultDetailPresenter;
    }

    @PerActivity
    @Provides
    LoadBeerInteractor detailInteractor(DefaultLoadBeerInteractor defaultDetailInteractor) {
        return defaultDetailInteractor;
    }



}