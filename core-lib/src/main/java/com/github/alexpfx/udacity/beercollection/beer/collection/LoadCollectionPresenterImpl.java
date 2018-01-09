package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.Logger;
import com.github.alexpfx.udacity.beercollection.beer.detail.BeerInteractor;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@PerActivity
public class LoadCollectionPresenterImpl implements LoadCollectionPresenter {

    private final MyCollectionInteractor collectionInteractor;
    private final SchedulerProvider provider;
    private final Logger logger;
    private MyCollectionView view;
    private BeerInteractor beerInteractor;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Inject
    public LoadCollectionPresenterImpl(MyCollectionInteractor collectionInteractor,
                                       SchedulerProvider
                                               provider, BeerInteractor beerInteractor, Logger logger) {


        this.collectionInteractor = collectionInteractor;
        this.provider = provider;
        this.beerInteractor = beerInteractor;
        this.logger = logger;
    }


    @Override
    public void load() {
        view.showLoading();

        Disposable disposable = collectionInteractor.load().timeout(Constants.TIMEOUT, TimeUnit.SECONDS).toFlowable()
                .flatMap
                        (Flowable::fromIterable)
                .flatMap(civo ->
                        Flowable
                                .zip(Flowable.just(civo), beerInteractor.load(civo
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
        onError.printStackTrace();
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
