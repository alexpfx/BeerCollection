package com.github.alexpfx.udacity.beercollection.databaselib.util;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 23/10/17.
 */

public interface FirebaseQueryAdapter<T> {

    Flowable<T> execute ();

}
