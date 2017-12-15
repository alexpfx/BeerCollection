package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.beercollection.BeerCollectionDataSource;
import com.github.alexpfx.udacity.beercollection.beer.BeerLocalDataSource;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItemVO;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
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
    BeerCollectionDataSource beerCollectionDataSource(FirebaseDatabase database, FirebaseAuth firebaseAuth) {
        return new BeerCollectionDataSource() {
            @Override
            public Single insert(DrinkBeerUpdateItem collectionItem) {
                return Single.create(subs -> {
                    DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid()
                    ).child("collection").push().getRef();

                    Map map = new HashMap();
                    map.put("beerId", collectionItem.getBeerId());
                    Integer quantity = collectionItem.getQuantity();
                    map.put("quantity", quantity);
                    map.put("timestamp", ServerValue.TIMESTAMP);
                    ref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            subs.onSuccess(quantity);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            subs.onError(e);
                        }
                    });
                } );
            }


            @Override
            public Single<List<CollectionItemVO>> all() {
                return Single.<List<CollectionItemVO>>create(emitter -> {
                    Query mycollection = database.getReference().child(firebaseAuth.getCurrentUser().getUid()).child
                            ("collection").orderByKey();

                    List<CollectionItemVO> items = new ArrayList<>();

                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                            for (DataSnapshot child : children) {
                                CollectionItemVO value = child.getValue(CollectionItemVO.class);
                                items.add(value);
                            }
                            emitter.onSuccess(items);
                            mycollection.removeEventListener(this);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            emitter.onError(new RuntimeException(databaseError.getMessage()));
                            mycollection.removeEventListener(this);
                        }
                    };
                    mycollection.addListenerForSingleValueEvent(eventListener);

                });

            }
        };
    }

    @Provides
    @Singleton
    BeerLocalDataSource beerDataSource(FirebaseDatabase database, FirebaseAuth firebaseAuth) {
        return new BeerLocalDataSource() {
            @Override
            public void insert(List<Beer> beers) {
                List<Beer> data = beers;
                if (data == null) {
                    return;
                }

                for (Beer beer : data) {
                    if (beer == null) continue;
                    database.getReference().child(firebaseAuth.getCurrentUser().getUid()).child("beers")
                            .child(beer.getId())
                            .setValue(beer);
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
        };

    }


}