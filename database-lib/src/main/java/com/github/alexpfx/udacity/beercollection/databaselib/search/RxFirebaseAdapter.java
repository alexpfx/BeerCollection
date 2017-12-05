package com.github.alexpfx.udacity.beercollection.databaselib.search;

import com.google.firebase.database.Query;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 23/10/17.
 */

interface RxFirebaseAdapter {


    Flowable create(Query query);
}
