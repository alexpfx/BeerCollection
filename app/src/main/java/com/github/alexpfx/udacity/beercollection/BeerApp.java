package com.github.alexpfx.udacity.beercollection;

import android.app.Application;
import android.content.Intent;

import com.github.alexpfx.udacity.beercollection.cache.CacheCleanerIntentService;
import com.github.alexpfx.udacity.beercollection.dagger.AndroidModule;
import com.github.alexpfx.udacity.beercollection.dagger.ApplicationComponent;
import com.github.alexpfx.udacity.beercollection.dagger.CacheCleanerModule;
import com.github.alexpfx.udacity.beercollection.dagger.CacheCleanerSubComponent;
import com.github.alexpfx.udacity.beercollection.dagger.DaggerApplicationComponent;
import com.github.alexpfx.udacity.beercollection.dagger.DetailModule;
import com.github.alexpfx.udacity.beercollection.dagger.DetailSubComponent;
import com.github.alexpfx.udacity.beercollection.dagger.MyCollectionModule;
import com.github.alexpfx.udacity.beercollection.dagger.MyCollectionSubComponent;
import com.github.alexpfx.udacity.beercollection.dagger.SearchModule;
import com.github.alexpfx.udacity.beercollection.dagger.SearchSubComponent;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.ServiceModule;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.config.BreweryDbConfig;


public class BeerApp extends Application {

    ApplicationComponent applicationComponent;

    private SearchSubComponent searchSubComponent;

    private DetailSubComponent detailSubComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        startCacheCleanerService();

        applicationComponent = createComponent();
    }


    private void startCacheCleanerService() {
        Intent intent = new Intent(this, CacheCleanerIntentService.class);
        intent.setAction(CacheCleanerIntentService.ACTION_CLEAN_OLD_CACHE_DATA);
        startService(intent);
    }


    private ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder().serviceModule(new ServiceModule(new BreweryDbConfig(BuildConfig
                .KEY_BREWERYDB))).androidModule(new AndroidModule(this)).build();
    }


    public ApplicationComponent getComponent() {
        return applicationComponent;
    }


    public SearchSubComponent getSearchSubComponent() {
        if (searchSubComponent == null) {
            searchSubComponent = applicationComponent.plus(new SearchModule());
        }
        return searchSubComponent;
    }


    public DetailSubComponent getDetailSubComponent() {
        detailSubComponent = applicationComponent.plus(new DetailModule());
        return detailSubComponent;
    }


    public MyCollectionSubComponent getMyCollectionSubComponent() {
        return applicationComponent.plus(new MyCollectionModule());
    }


    public CacheCleanerSubComponent getCacheCleanerSubComponent() {
        return applicationComponent.plus(new CacheCleanerModule());
    }
}
