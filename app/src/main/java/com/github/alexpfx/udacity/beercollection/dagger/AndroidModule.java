package com.github.alexpfx.udacity.beercollection.dagger;

import android.content.Context;
import android.content.res.Resources;

import com.github.alexpfx.udacity.beercollection.AndroidSchedulerProvider;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.util.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 14/10/17.
 */

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
    SchedulerProvider schedulerProvider (AndroidSchedulerProvider schedulerProvider){
        return schedulerProvider;
    }


}
