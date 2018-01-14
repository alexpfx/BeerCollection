package com.github.alexpfx.udacity.beercollection.beer.beer;

import com.github.alexpfx.udacity.beercollection.beer.BeerLocalDataSource;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.search.BeerRemoteDataSource;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * Created by alexandre on 04/11/17.
 */

@PerActivity
public class BeerInteractorImpl implements BeerInteractor {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private BeerLocalDataSource local;
    private SchedulerProvider schedulerProvider;

    private BeerRemoteDataSource remote;


    private Consumer<? super Throwable> onError = (Consumer<Throwable>) throwable -> {
        logger.log(Level.WARNING, "error loading from remote repository");
    };

    @Inject
    public BeerInteractorImpl(BeerLocalDataSource local, BeerRemoteDataSource remote, SchedulerProvider
            schedulerProvider) {
        this.local = local;
        this.schedulerProvider = schedulerProvider;
        Preconditions.checkNotNull(remote, "remote cannot be null");
        this.remote = remote;
    }


    //https://medium.com/@iammert/offline-app-with-rxjava-2-and-room-ccd0b5c18101
    // https://android.jlelse.eu/rxjava-2-single-concat-sample-for-repository-pattern-1873c456227a

    /**
     * Ao carregar dados de uma cerveja, a busca é feita primeiramente no cache (Local). Uma vez
     * que o cache pode ter sido apagado pelo job de limpeza (CacheCleanerIntentService) é
     * possível que a informação não esteja disponível no cache, sendo necessário uma nova
     * consulta ao serviço Rest (Remote).
     * Este método usa operadores do RxJava para combinar uma consulta ao cache local e uma
     * consulta remota, de modo que a primeira resposta de um dos serviços é emitida.
     * Caso a resposta seja proveniente da consulta remota, o valor retornado é salvo no banco antes de ser emitido.
     *
     * <p>
     * TODO: traduzir
     *
     * @param beerId
     * @return
     */
    @Override
    public Flowable<Beer> load(String beerId) {
        return Flowable.create(emitter -> Flowable.concat(
                local.load(beerId).toFlowable()
                        .subscribeOn(schedulerProvider.computation())
                        .observeOn(schedulerProvider.computation()),
                remote.load(beerId).toFlowable()).first(new Beer())
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.computation())
                .subscribe(beer -> {
                            local.insert(beer);
                            emitter.onNext(beer);
                        },

                        emitter::onError
                ), BackpressureStrategy.BUFFER);
    }

    @Override
    public Single<Void> clearCache(long elapsedTime) {
        return local.clearCache(elapsedTime);
    }


}
