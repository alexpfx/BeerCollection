package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.cache.CacheCleanerIntentService;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;

import dagger.Subcomponent;

/**
 * Created by alexandre on 08/01/18.
 */

@PerActivity
@Subcomponent(modules = {CacheCleanerModule.class, BeerModule.class})
public interface CacheCleanerSubComponent  {
    void inject (CacheCleanerIntentService cacheCleanerIntentService);
}