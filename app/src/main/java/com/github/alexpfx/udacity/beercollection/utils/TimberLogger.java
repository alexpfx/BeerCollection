package com.github.alexpfx.udacity.beercollection.utils;

import com.github.alexpfx.udacity.beercollection.Logger;

import timber.log.Timber;

/**
 * Created by alexandre on 25/11/17.
 */
public class TimberLogger implements Logger {

    @Override
    public void d(String message, Object... param) {
        setTag();
        Timber.d(message, param);
    }

    private void setTag() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length >= 3){
            Timber.tag(stackTrace[3].getClassName());
        }
    }

    @Override
    public void e(Throwable throwable) {
        setTag();
        Timber.e(throwable);
    }
}
