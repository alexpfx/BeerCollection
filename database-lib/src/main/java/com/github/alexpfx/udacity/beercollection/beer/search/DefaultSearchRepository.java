package com.github.alexpfx.udacity.beercollection.beer.search;

import com.github.alexpfx.udacity.beercollection.domain.client.BreweryDBService;
import com.github.alexpfx.udacity.beercollection.domain.model.database.FireBeer;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 17/10/17.
 */

public class DefaultSearchRepository implements SearchRepository{

    private final FirebaseDatabase firebaseDatabase;
    private final BreweryDBService breweryDBService;

    @Inject
    public DefaultSearchRepository(FirebaseDatabase firebaseDatabase, BreweryDBService breweryDBService) {
        this.firebaseDatabase = firebaseDatabase;
        this.breweryDBService = breweryDBService;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public Flowable<FireBeer> search(String name) {

        return null;
    }
}
