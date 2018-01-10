package com.github.alexpfx.udacity.beercollection.dagger;

import android.app.Activity;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;

import dagger.Module;
import dagger.Provides;


@Module

public class ActivityModule {
    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }


    @PerActivity
    @Provides
    public Activity activity (){
        return this.activity;

    }
}
