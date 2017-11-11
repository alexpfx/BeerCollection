package com.github.alexpfx.udacity.beercollection.util;

import io.reactivex.Scheduler;

/**
 * Created by alexandre on 15/10/17.
 */

public interface SchedulerProvider {
    Scheduler computation();

    Scheduler io();

    Scheduler mainThread();
}
