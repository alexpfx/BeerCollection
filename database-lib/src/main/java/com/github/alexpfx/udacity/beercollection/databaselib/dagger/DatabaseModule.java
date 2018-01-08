package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.BeerCollectionDataSource;
import com.github.alexpfx.udacity.beercollection.BeerCollectionDataSourceImpl;
import com.github.alexpfx.udacity.beercollection.beer.BeerLocalDataSource;
import com.github.alexpfx.udacity.beercollection.databaselib.BeerLocalDataSourceImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 17/10/17.
 */
@Module
public class DatabaseModule {

    public DatabaseModule() {
    }


    @Provides
    @Singleton
    FirebaseAuth firebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    FirebaseDatabase firebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }


    @Provides
    @Singleton
    BeerCollectionDataSource beerCollectionDataSource(FirebaseDatabase database, FirebaseAuth firebaseAuth) {
        return new BeerCollectionDataSourceImpl(database, firebaseAuth);
    }

    @Provides
    @Singleton
    BeerLocalDataSource beerDataSource(FirebaseDatabase database, FirebaseAuth firebaseAuth) {
        return new BeerLocalDataSourceImpl(database, firebaseAuth);

    }


}