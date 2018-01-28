package com.github.alexpfx.udacity.beercollection.databaselib.util;

import io.reactivex.Flowable;


public interface FirebaseQueryAdapter<T> {

    Flowable<T> execute ();

}
