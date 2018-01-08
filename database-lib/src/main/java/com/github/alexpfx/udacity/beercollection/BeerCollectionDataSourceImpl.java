package com.github.alexpfx.udacity.beercollection;

import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItemVO;
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
    public static final String COLLECTION_CHILD = "collection";
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
            DatabaseReference refCollection = refUser.child(COLLECTION_CHILD);

            Map map = new HashMap();
            Integer quantity = collectionItem.getQuantity();
            map.put("beerId", collectionItem.getBeerId());
            map.put("quantity", quantity);
            map.put("timestamp", ServerValue.TIMESTAMP);

            refCollection.push()
                    .setValue(map)
                    .addOnSuccessListener(aVoid -> subscribe.onSuccess(quantity))
                    .addOnFailureListener(e -> subscribe.onError(e));

        });
    }

    @Override
    public Single<List<CollectionItemVO>> all() {
        return Single.<List<CollectionItemVO>>create(emitter -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid()).child
                    (COLLECTION_CHILD);

            List<CollectionItemVO> items = new ArrayList<>();


            final Query myCollection = ref.orderByKey();


            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                    for (DataSnapshot child : children) {
                        CollectionItemVO valuex = child.getValue(CollectionItemVO.class);
                        items.add(valuex);
                    }
                    emitter.onSuccess(items);
                    myCollection.removeEventListener(this);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    emitter.onError(new RuntimeException(databaseError.getMessage()));
                    myCollection.removeEventListener(this);
                }
            };

            myCollection.addListenerForSingleValueEvent(eventListener);

        });

    }

    @Override
    public Single clearCollectionData() {
        return Single.create(emitter -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid()
            ).child("collection");
            ref.setValue(null)
                    .addOnSuccessListener(aVoid -> {
                        emitter.onSuccess(0);
                    })
                    .addOnFailureListener(emitter::onError);

        });
    }

    @Override
    public Single deleteBeerFromCollection(String id) {
        return Single.create(emitter -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid()).child(COLLECTION_CHILD);

            Query query = ref.orderByChild("beerId").equalTo(id);

            ValueEventListener v = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        ref.child(child.getKey()).setValue(null).addOnSuccessListener(aVoid -> emitter.onSuccess(id));
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    emitter.onError(databaseError.toException());
                }
            };

            query.addListenerForSingleValueEvent(v);
        });
    }
}
