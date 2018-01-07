package com.github.alexpfx.udacity.beercollection;

import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItemVO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    public static final String COLLECTION_INDEX = "collection-index";
    private static final String TAG = "BeerCollectionDataSourc";
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;

    public BeerCollectionDataSourceImpl(FirebaseDatabase database, FirebaseAuth firebaseAuth) {
        this.database = database;
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public Single insert(DrinkBeerUpdateItem collectionItem) {
        return Single.create(subscribe -> {
            DatabaseReference refUser = database.getReference()
                    .child(firebaseAuth.getCurrentUser().getUid());
            DatabaseReference refCollection = refUser.child("collection");

            Map map = new HashMap();
            Integer quantity = collectionItem.getQuantity();
            map.put("beerId", collectionItem.getBeerId());
            map.put("quantity", quantity);
            map.put("timestamp", ServerValue.TIMESTAMP);

            String key = refCollection.push().getKey();
            refCollection.child(key)
                    .setValue(map)
                    .addOnSuccessListener(aVoid -> subscribe.onSuccess(quantity))
                    .addOnFailureListener(e -> subscribe.onError(e));

            refUser.child(COLLECTION_INDEX).child(key).setValue(true);
        });
    }

    @Override
    public Single<List<CollectionItemVO>> all() {
        return Single.<List<CollectionItemVO>>create(emitter -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid()).child
                    (COLLECTION_INDEX);


            List<CollectionItemVO> items = new ArrayList<>();

            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                    for (DataSnapshot child : children) {
                        HashMap<String, CollectionItem> value = (HashMap<String, CollectionItem>) child.getValue();
                        CollectionItemVO valuex = child.getValue(CollectionItemVO.class);
                        items.add(valuex);
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
                    .addOnSuccessListener(aVoid -> {
                        subscribe.onSuccess(0);
                    })
                    .addOnFailureListener(e -> subscribe.onError(e));

        });
    }

    @Override
    public Single deleteBeerFromCollection(String id) {
        DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid());


        return null;
    }
}
