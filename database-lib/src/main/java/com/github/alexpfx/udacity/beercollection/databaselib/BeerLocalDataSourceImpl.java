package com.github.alexpfx.udacity.beercollection.databaselib;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.FlowableEmitter;
import io.reactivex.Maybe;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by alexandre on 08/01/18.
 */

public class BeerLocalDataSourceImpl implements BeerLocalDataSource {
    private static final String TAG = "BeerLocalDataSourceImpl";
    private final FirebaseDatabase database;
    private final FirebaseAuth firebaseAuth;
    AtomicInteger count = new AtomicInteger();

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
    public void insert(Beer beer) {
        database.getReference().child(firebaseAuth.getCurrentUser().getUid()).child("beers").child(beer.getId())
                .setValue(beer);
    }

    @Override
    public void load(String id, FlowableEmitter<Beer> emitter) {
        DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser()
                .getUid())
                .child("beers").child(id);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Beer beer = dataSnapshot.getValue(Beer.class);
                if (beer != null) {
                    emitter.onNext(beer);
                } else {
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                emitter.onError(new Throwable(databaseError.getMessage()));
                ref.removeEventListener(this);
            }
        };
        ref.addValueEventListener(valueEventListener);
        emitter.setCancellable(() -> ref.removeEventListener(valueEventListener));
    }

    @Override
    public Maybe<Beer> load(String id) {
        Timber.d("load %s", id);

        return Maybe.create(emitter -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser()
                    .getUid())
                    .child("beers").child(id);


            //https://stackoverflow
            // .com/questions/36330776/combining-firebase-realtime-data-listener-with
            // -rxjava/37328358

            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Beer beer = dataSnapshot.getValue(Beer.class);
                    if (beer != null) {
                        emitter.onSuccess(beer);
                    } else {
                        emitter.onComplete();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    emitter.onError(new Throwable(databaseError.getMessage()));
                    ref.removeEventListener(this);
                }
            };
            ref.addValueEventListener(valueEventListener);
            emitter.setCancellable(() -> ref.removeEventListener(valueEventListener));
        });

    }

    @Override
    public Single<Integer> clearCache(long elapsedTime) {
        return Single.create(emitter -> {
            if (firebaseAuth == null || firebaseAuth.getCurrentUser() == null) {
                return;
            }

            DatabaseReference userRef = database.getReference().child(firebaseAuth.getCurrentUser().getUid());

            DatabaseReference lastUpdateRef = userRef
                    .child("beers-last-update");

            DatabaseReference beersRef = userRef.child("beers");

            long target = System.currentTimeMillis() - elapsedTime;


            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();


                    count.set(0);
                    Map<String, Object> toDelete = new HashMap<>();
                    for (DataSnapshot child : children) {
                        String beerId = child.getKey();
                        toDelete.put("/" + beersRef, null);
                        count.addAndGet(1);
                    }

                    beersRef.updateChildren(Collections.emptyMap(), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            emitter.onSuccess(count.get());
                            lastUpdateRef.removeValue();
                        }
                    });


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }
            };


            Query query = lastUpdateRef.orderByChild("timestamp").endAt(target);
            query.addListenerForSingleValueEvent(valueEventListener);
        });
    }


}
