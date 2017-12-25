package com.github.alexpfx.udacity.beercollection;

import android.support.annotation.NonNull;

import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
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

import io.reactivex.Single;

/**
 * Created by alexandre on 24/12/17.
 */

public class BeerCollectionDataSourceImpl implements BeerCollectionDataSource {
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;

    public BeerCollectionDataSourceImpl(FirebaseDatabase database, FirebaseAuth firebaseAuth) {
        this.database = database;
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public Single insert(DrinkBeerUpdateItem collectionItem) {
        return Single.create(subscribe -> {
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
                    subscribe.onSuccess(quantity);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    subscribe.onError(e);
                }
            });
        });
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

    @Override
    public Single clearCollectionData() {
        return Single.create(subscribe -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid()
            ).child("collection");
            ref.setValue(null)
                    .addOnSuccessListener(aVoid -> subscribe.onSuccess(aVoid))
                    .addOnFailureListener(e -> subscribe.onError(e));

        });
    }

}
