package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.collection.DefaultMyCollectionInteractor;
import com.github.alexpfx.udacity.beercollection.beer.collection.DefaultMyCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionInteractor;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 09/11/17.
 */

@Module
public class MyCollectionModule {

    @Provides
    @MyCollectionScope
    MyCollectionInteractor myCollectionInteractor (DefaultMyCollectionInteractor defaultMyCollectionInteractor){
        return defaultMyCollectionInteractor;
    }

    @Provides
    @MyCollectionScope
    MyCollectionPresenter myCollectionPresenter(DefaultMyCollectionPresenter defaultMyCollectionPresenter){
        return defaultMyCollectionPresenter;
    }


}
