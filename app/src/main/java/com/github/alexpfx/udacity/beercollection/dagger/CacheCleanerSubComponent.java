package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.cache.CacheCleanerIntentService;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {CacheCleanerModule.class, BeerModule.class})
public interface CacheCleanerSubComponent {
    void inject(CacheCleanerIntentService cacheCleanerIntentService);
}
