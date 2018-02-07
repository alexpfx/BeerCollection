package com.github.alexpfx.udacity.beercollection.utils;

import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class AndroidSchedulerProvider implements SchedulerProvider {

    @Inject
    public AndroidSchedulerProvider() {
    }


    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }


    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}
