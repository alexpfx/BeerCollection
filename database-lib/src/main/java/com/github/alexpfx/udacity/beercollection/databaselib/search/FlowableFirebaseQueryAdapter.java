package com.github.alexpfx.udacity.beercollection.databaselib.search;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Emitter;
import io.reactivex.Flowable;


public class FlowableFirebaseQueryAdapter<T> implements RxFirebaseAdapter {

    @Override
    public Flowable<T> create(Query query) {
        return Flowable.create(emitter -> {
            ChildEvent ce = new ChildEvent(emitter);
            query.addChildEventListener(ce);
            emitter.setCancellable(() -> query.removeEventListener(ce));
        }, BackpressureStrategy.BUFFER);


    }

    private static final String TAG = "FlowableFirebaseQueryAd";

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
