package com.github.alexpfx.udacity.beercollection.beer.search;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 23/10/17.
 */

public interface FirebaseQuery <T> {

    Flowable<T> execute ();

}
