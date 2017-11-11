package com.github.alexpfx.udacity.beercollection;

import com.github.alexpfx.udacity.beercollection.util.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alexandre on 15/10/17.
 */
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
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}
