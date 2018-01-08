package com.github.alexpfx.udacity.beercollection.databaselib;

import android.util.Log;

import com.github.alexpfx.udacity.beercollection.beer.BeerLocalDataSource;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by alexandre on 08/01/18.
 */
public class BeerLocalDataSourceImpl implements BeerLocalDataSource {
    private final FirebaseDatabase database;
    private final FirebaseAuth firebaseAuth;

    public BeerLocalDataSourceImpl(FirebaseDatabase database, FirebaseAuth firebaseAuth) {
        this.database = database;
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public void insert(List<Beer> beers) {
        List<Beer> data = beers;
        if (data == null) {
            return;
        }

        for (Beer beer : data) {
            if (beer == null) {
                continue;
            }
            DatabaseReference userReference = database.getReference().child(firebaseAuth.getCurrentUser()
                    .getUid());

            userReference.child("beers")
                    .child(beer.getId())
                    .setValue(beer);

            /* adds a timestamp that denotes the last update, allowing cleaning the cache after information
            expires */
            userReference.child("beers-last-update").child(beer.getId())
                    .setValue(Collections.singletonMap("timestamp", ServerValue.TIMESTAMP));

        }
    }

    @Override
    public Flowable<Beer> load(String id) {
        return Flowable.create(e -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser()
                    .getUid())
                    .child("beers").child(id);


            //https://stackoverflow
            // .com/questions/36330776/combining-firebase-realtime-data-listener-with
            // -rxjava/37328358

            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    e.onNext(dataSnapshot.getValue(Beer.class));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    e.onError(new Throwable(databaseError.getMessage()));
                    ref.removeEventListener(this);
                }
            };
            ref.addValueEventListener(valueEventListener);
            e.setCancellable(() -> ref.removeEventListener(valueEventListener));
        }, BackpressureStrategy.BUFFER);
    }

    private static final String TAG = "BeerLocalDataSourceImpl";

    @Override
    public Single<Integer> clearCache(long elapsedTime) {
        return Single.create(emitter -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid()).child("beers-last-update");
            long target = elapsedTime + new Date().getTime();

            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d(TAG, "onDataChange: "+dataSnapshot);
                    emitter.onSuccess(10);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            Query query = ref.orderByChild("timestamp").startAt(target);
            query.addListenerForSingleValueEvent(valueEventListener);
        });
    }


}
