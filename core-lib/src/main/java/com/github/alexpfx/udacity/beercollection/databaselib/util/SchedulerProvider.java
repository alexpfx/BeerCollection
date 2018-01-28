package com.github.alexpfx.udacity.beercollection.databaselib.util;

import io.reactivex.Scheduler;


public interface SchedulerProvider {
    Scheduler computation();

    Scheduler mainThread();
}
