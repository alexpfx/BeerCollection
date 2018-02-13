package com.github.alexpfx.udacity.beercollection;

import com.github.alexpfx.udacity.beercollection.Constants.Beer;
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
import io.reactivex.SingleEmitter;

public class BeerCollectionDataSourceImpl implements BeerCollectionDataSource {
    public static final String COLLECTION_CHILD = "collection";
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
            map.put(Beer.BEER_ID, collectionItem.getBeerId());
            map.put(Beer.QUANTITY, quantity);
            map.put(Beer.TIMESTAMP, ServerValue.TIMESTAMP);

            refCollection.push()
                    .setValue(map)
                    .addOnSuccessListener(aVoid -> subscribe.onSuccess(quantity))
                    .addOnFailureListener(e -> subscribe.onError(e));

        });
    }

    @Override
    public Single<List<CollectionItemVO>> all() {
        return Single.create(source -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid()).child
                    (COLLECTION_CHILD);
            List<CollectionItemVO> items = new ArrayList<>();


            final Query myCollection = ref.orderByKey();


            ValueEventListener eventListener = new QueryAllEventListener(items, source, myCollection);

            myCollection.addListenerForSingleValueEvent(eventListener);

        });

    }

    @Override
    public Single clearCollectionData() {
        return Single.create(emitter -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid()
            ).child("collection");
            ref.setValue(null)
                    .addOnSuccessListener(aVoid -> emitter.onSuccess(0))
                    .addOnFailureListener(emitter::onError);

        });
    }

    @Override
    public Single deleteBeerFromCollection(String id) {
        return Single.create(emitter -> {
            DatabaseReference ref = database.getReference().child(firebaseAuth.getCurrentUser().getUid()).child(COLLECTION_CHILD);

            Query query = ref.orderByChild(Beer.BEER_ID).equalTo(id);

            ValueEventListener v = new DeleteBeerEventListener(ref, emitter, id);

            query.addListenerForSingleValueEvent(v);
        });
    }


    private static class DeleteBeerEventListener implements ValueEventListener {

        private final DatabaseReference ref;

        private final SingleEmitter<Object> emitter;

        private final String id;


        public DeleteBeerEventListener(DatabaseReference ref, SingleEmitter<Object> emitter, String id) {
            this.ref = ref;
            this.emitter = emitter;
            this.id = id;
        }


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
    }

    private static class QueryAllEventListener implements ValueEventListener {

        private final List<CollectionItemVO> items;

        private final SingleEmitter<List<CollectionItemVO>> source;

        private final Query myCollection;


        public QueryAllEventListener(List<CollectionItemVO> items, SingleEmitter<List<CollectionItemVO>> source,
                                     Query myCollection) {
            this.items = items;
            this.source = source;
            this.myCollection = myCollection;
        }


        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Iterable<DataSnapshot> children = dataSnapshot.getChildren();

            for (DataSnapshot child : children) {
                CollectionItemVO valuex = child.getValue(CollectionItemVO.class);
                items.add(valuex);
            }
            source.onSuccess(items);
            myCollection.removeEventListener(this);
        }


        @Override
        public void onCancelled(DatabaseError databaseError) {
            source.onError(new RuntimeException(databaseError.getMessage()));
            myCollection.removeEventListener(this);
        }
    }
}
