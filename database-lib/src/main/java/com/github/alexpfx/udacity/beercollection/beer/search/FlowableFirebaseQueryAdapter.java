package com.github.alexpfx.udacity.beercollection.beer.search;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.functions.Cancellable;

/**
 * Created by alexandre on 23/10/17.
 */

public class BaseFirebaseQuery<T> implements FirebaseQuery<T> {


    private final Query query;
    private ChildEvent ce;

    public BaseFirebaseQuery(Query query) {
        this.query = query;
    }


    @Override
    public Flowable<T> execute() {
        return Flowable.create(emitter -> {
            ce = new ChildEvent(emitter);
            query.addChildEventListener(ce);

            emitter.setCancellable(new Cancellable() {
                @Override
                public void cancel() throws Exception {
                    query.removeEventListener(ce);
                }
            });

        }, BackpressureStrategy.BUFFER);


    }

    class ChildEvent implements ChildEventListener {

        private Emitter<T> emitter;

        public ChildEvent(Emitter<T> emitter) {
            this.emitter = emitter;
        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            emitter.onNext((T) dataSnapshot.getValue());
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            emitter.onError(new RuntimeException(databaseError.getMessage()));
        }
    }


}
