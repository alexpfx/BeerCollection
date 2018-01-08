package com.github.alexpfx.udacity.beercollection.beer.detail;

import com.github.alexpfx.udacity.beercollection.beer.BeerLocalDataSource;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.search.BeerRemoteDataSource;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * Created by alexandre on 04/11/17.
 */

@PerActivity
public class LoadBeerInteractorImpl implements LoadBeerInteractor {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private BeerLocalDataSource local;
    private SchedulerProvider schedulerProvider;
    private BeerRemoteDataSource remote;


    private Consumer<? super Throwable> onError = (Consumer<Throwable>) throwable -> {
        logger.log(Level.WARNING, "error loading from remote repository");
    };

    @Inject
    public LoadBeerInteractorImpl(BeerLocalDataSource local, BeerRemoteDataSource remote, SchedulerProvider schedulerProvider) {
        this.local = local;
        this.schedulerProvider = schedulerProvider;
        this.remote = remote;
    }



    //https://medium.com/@iammert/offline-app-with-rxjava-2-and-room-ccd0b5c18101
    @Override
    public Flowable<Beer> load(String beerId) {
        Flowable<Beer> localFlowable = local.load(beerId);



//        Single<LocalType<Beer>> remoteCache = remote.load(beerId).cache();
//
//        remoteCache.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.io()).subscribe(
//                onSuccess, onError
//        );

        return localFlowable;
    }

    @Override
    public Single<Integer> clearCache(long elapsedTime) {
        return local.clearCache(elapsedTime);
    }




}
