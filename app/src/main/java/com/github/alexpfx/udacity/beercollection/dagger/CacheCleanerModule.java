package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.CacheCleanerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.CacheCleanerPresenterImpl;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheCleanerModule {

    @PerActivity
    @Provides
    public CacheCleanerPresenter cacheCleanerPresenter(CacheCleanerPresenterImpl cacheCleanerPresenter) {
        return cacheCleanerPresenter;
    }


}
