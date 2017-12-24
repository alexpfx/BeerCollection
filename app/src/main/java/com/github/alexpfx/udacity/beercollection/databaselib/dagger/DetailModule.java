package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.beer.detail.DetailPresenterImpl;
import com.github.alexpfx.udacity.beercollection.beer.detail.LoadBeerInteractorImpl;
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
    DetailPresenter detailPresenter(DetailPresenterImpl detailPresenterImpl) {
        detailPresenterImpl.bind(detailView);
        return detailPresenterImpl;
    }

    @PerActivity
    @Provides
    LoadBeerInteractor detailInteractor(LoadBeerInteractorImpl defaultDetailInteractor) {
        return defaultDetailInteractor;
    }



}
