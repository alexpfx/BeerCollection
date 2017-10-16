package com.github.alexpfx.udacity.beercollection;

import android.content.Context;
import android.content.res.Resources;

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


}
