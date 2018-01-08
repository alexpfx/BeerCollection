package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.beer.CacheCleanerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.CacheCleanerPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 08/01/18.
 */

@Module
public class CacheCleanerModule {

    @PerActivity
    @Provides
    public CacheCleanerPresenter cacheCleanerPresenter(CacheCleanerPresenterImpl cacheCleanerPresenter) {
        return cacheCleanerPresenter;
    }


}
