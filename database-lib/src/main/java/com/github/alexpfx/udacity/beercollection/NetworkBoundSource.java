package com.github.alexpfx.udacity.beercollection;

import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by alexandre on 29/10/17.
 */
// https://medium.com/@iammert/offline-app-with-rxjava-2-and-room-ccd0b5c18101
public abstract class NetworkBoundSource<L, R> {

    public NetworkBoundSource(FlowableEmitter<L> emitter) {
        getLocal().subscribe(emitter::onNext);
    }

    public abstract Single<List<Beer>> getRemote();

    public abstract Flowable<L> getLocal();

    public abstract void save(L data);

    public abstract Function<R, L> mapper();

}
