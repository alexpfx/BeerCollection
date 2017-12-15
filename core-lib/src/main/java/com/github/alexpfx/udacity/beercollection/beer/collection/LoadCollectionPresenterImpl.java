package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.Logger;
import com.github.alexpfx.udacity.beercollection.beer.detail.LoadBeerInteractor;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by alexandre on 09/11/17.
 */
@PerActivity
public class LoadCollectionPresenterImpl implements LoadCollectionPresenter {

    private final MyCollectionInteractor collectionInteractor;
    private final SchedulerProvider provider;
    private MyCollectionView view;
    private Disposable subscription;
    private LoadBeerInteractor loadBeerInteractor;
    private final Logger logger;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Inject
    public LoadCollectionPresenterImpl(MyCollectionInteractor collectionInteractor, MyCollectionView view,
                                       SchedulerProvider
                                               provider, LoadBeerInteractor loadBeerInteractor, Logger logger) {


        this.collectionInteractor = collectionInteractor;
        this.provider = provider;
        this.view = view;
        this.loadBeerInteractor = loadBeerInteractor;
        this.logger = logger;
    }


    @Override
    public void load() {
        view.showLoading();
        logger.d("load");

        Disposable disposable = collectionInteractor.load().timeout(Constants.TIMEOUT, TimeUnit.SECONDS).toFlowable()
                .flatMap
                (Flowable::fromIterable)
                .flatMap(civo ->
                        Flowable
                                .zip(Flowable.just(civo), loadBeerInteractor.load(civo
                                        .getBeerId()), (civo1, beer) -> new CollectionItem(beer,
                                        new ArrayList<>(Arrays.asList(civo1)))))
                .groupBy(collectionItem -> collectionItem.getBeer().getId())
                .flatMap(groups -> Flowable.fromCallable(() ->
                        groups.collect(CollectionItem::new, CollectionItem::merge)))
                .flatMap(Single::toFlowable).toList()
                .subscribeOn(provider.computation())
                .observeOn(provider.mainThread())
                .subscribe(
                        this::handleCollection,
                        this::handleError);

        compositeDisposable.add(disposable);

    }

    private void handleCollection(List<CollectionItem> collectionItems) {
        view.hideLoading();
        logger.d("handleCollection", collectionItems);
        if (collectionItems.isEmpty()) {
            view.showCollectionEmpty();
        } else {
            view.showUserCollection(collectionItems);
        }
    }


    private void handleError(Throwable onError) {
        view.hideLoading();
        view.showErrorLoadingCollection(onError.getMessage());
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }

    @Override
    public void bind(MyCollectionView view) {
        this.view = view;
    }
}
