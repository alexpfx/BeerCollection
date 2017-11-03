package com.github.alexpfx.udacity.beercollection;

import android.app.Activity;
import android.app.Application;

import com.github.alexpfx.udacity.beercollection.beer.search.SearchView;
import com.github.alexpfx.udacity.beercollection.dagger.ActivityModule;
import com.github.alexpfx.udacity.beercollection.dagger.AndroidModule;
import com.github.alexpfx.udacity.beercollection.dagger.ApplicationComponent;
import com.github.alexpfx.udacity.beercollection.dagger.DaggerApplicationComponent;
import com.github.alexpfx.udacity.beercollection.dagger.SearchModule;
import com.github.alexpfx.udacity.beercollection.dagger.SearchSubComponent;
import com.github.alexpfx.udacity.beercollection.dagger.ServiceModule;
import com.github.alexpfx.udacity.beercollection.domain.model.remote.config.BreweryDbConfig;

/**
 * Created by alexandre on 14/10/17.
 */

public class BeerApp extends Application {

    ApplicationComponent applicationComponent;
    private SearchSubComponent searchSubComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = createComponent();
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(IconicsContextWrapper.wrap(base));
//    }

    private ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder().serviceModule(new ServiceModule(new BreweryDbConfig(BuildConfig
                .KEY_BREWERYDB))).androidModule(new AndroidModule(this)).build();

    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public SearchSubComponent getSearchSubComponent(Activity activity) {
        if (searchSubComponent == null) {
            searchSubComponent = applicationComponent.plus(new SearchModule((SearchView) activity), new ActivityModule(activity));
        }
        return searchSubComponent;
    }



}
