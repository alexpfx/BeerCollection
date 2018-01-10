package com.github.alexpfx.udacity.beercollection.dagger;

import android.content.Context;
import android.content.res.Resources;

import com.github.alexpfx.udacity.beercollection.utils.AndroidSchedulerProvider;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Logger;
import com.github.alexpfx.udacity.beercollection.utils.TimberLogger;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AndroidModule {

    private BeerApp beerApp;

    public AndroidModule(BeerApp beerApp) {
        this.beerApp = beerApp;
    }

    @Provides
    @Singleton
    Context context() {
        return beerApp.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources resources() {
        return beerApp.getResources();
    }


    @Provides
    @Singleton
    SchedulerProvider schedulerProvider(AndroidSchedulerProvider schedulerProvider) {
        return schedulerProvider;
    }


    @Provides
    @Singleton
    Logger logger() {
        return new TimberLogger();
    }


}
