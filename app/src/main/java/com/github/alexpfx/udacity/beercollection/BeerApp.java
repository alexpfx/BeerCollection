package com.github.alexpfx.udacity.beercollection;

import android.app.Application;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.github.alexpfx.udacity.beercollection.beer.detail.DetailView;
import com.github.alexpfx.udacity.beercollection.collection.MyCollectionSubComponent;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.AndroidModule;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.ApplicationComponent;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.DaggerApplicationComponent;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.DetailModule;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.DetailSubComponent;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.MyCollectionModule;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.SearchModule;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.SearchSubComponent;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.ServiceModule;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.config.BreweryDbConfig;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;


public class BeerApp extends Application {

    ApplicationComponent applicationComponent;
    MyCollectionSubComponent myCollectionSubComponent;
    private SearchSubComponent searchSubComponent;
    private DetailSubComponent detailSubComponent;

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(IconicsContextWrapper.wrap(base));
//    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        startCacheCleanerService();

        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...


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

    public DetailSubComponent getDetailSubComponent(Fragment fragment) {
        detailSubComponent = applicationComponent.plus(new DetailModule((DetailView) fragment));
        return detailSubComponent;
    }


    public MyCollectionSubComponent getMyCollectionSubComponent() {
        return applicationComponent.plus(new MyCollectionModule());
    }



}
