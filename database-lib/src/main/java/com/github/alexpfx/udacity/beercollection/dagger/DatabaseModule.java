package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.BeerDataSource;
import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.local.LocalType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Single;

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
    BeerDataSource beerDataSource(FirebaseDatabase database, FirebaseAuth firebaseAuth) {

        return new BeerDataSource() {
            @Override
            public void insert(LocalType<List<Beer>> beers) {
                List<Beer> data = beers.getData();
                if (data == null){
                    return;
                }

                for (Beer beer : data) {
                    firebaseDatabase().getReference().child(firebaseAuth.getCurrentUser().getUid()).child("beers")
                            .child(beer.getId())
                            .setValue(beer);
                }

            }

            @Override
            public Single<LocalType<Beer>> load(String id) {
                return null;
            }
        };

    }


}
