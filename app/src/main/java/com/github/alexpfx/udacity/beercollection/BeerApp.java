package com.github.alexpfx.udacity.beercollection;

import android.app.Activity;
import android.app.Application;

import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerView;
import com.github.alexpfx.udacity.beercollection.beer.collection.MyCollectionView;
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
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchView;
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

        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }

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

    private ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder().serviceModule(new ServiceModule(new BreweryDbConfig(BuildConfig
                .KEY_BREWERYDB))).androidModule(new AndroidModule(this)).build();

    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public SearchSubComponent getSearchSubComponent(Activity activity) {
        if (searchSubComponent == null) {
            searchSubComponent = applicationComponent.plus(new SearchModule((SearchView) activity));
        }
        return searchSubComponent;
    }

    public DetailSubComponent getDetailSubComponent(Activity activity) {
        detailSubComponent = applicationComponent.plus(new DetailModule((DetailView) activity));
        return detailSubComponent;
    }

    public MyCollectionSubComponent getMyCollectionSubComponent(DrinkBeerView drinkBeerView, MyCollectionView myCollectionView) {
        return applicationComponent.plus(new MyCollectionModule(drinkBeerView, myCollectionView));
    }

    public MyCollectionSubComponent getMyCollectionSubComponent(DrinkBeerView drinkBeerView) {
        return applicationComponent.plus(new MyCollectionModule(drinkBeerView));

    }

}
