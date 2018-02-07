package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.beer.beer.BeerInteractor;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
    private CompositeDisposable compositeDisposable;
    private MyCollectionView view;
    private BeerInteractor beerInteractor;



    @Inject
    public LoadCollectionPresenterImpl(MyCollectionInteractor collectionInteractor,
                                       SchedulerProvider
                                               provider, BeerInteractor beerInteractor) {


        this.collectionInteractor = collectionInteractor;
        this.provider = provider;
        this.beerInteractor = beerInteractor;
    }


    @Override
    public void load(Comparator<CollectionItem> comparator) {
        view.showLoading();


        /*TODO explicar
        O trecho abaixo utiliza operadores RxJava para basicamente: ouvir duas fontes de dados
         */
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
                        collectionItems -> handleCollection(collectionItems, comparator),
                        this::handleError);

        compositeDisposable.add(disposable);

    }

    private void handleCollection(List<CollectionItem> collectionItems, Comparator<CollectionItem> comparator) {
        view.hideLoading();
        if (collectionItems.isEmpty()) {
            view.showCollectionEmpty();
        } else {
            Collections.sort(collectionItems, comparator);
            view.showUserCollection(collectionItems);
        }
    }


    private void handleError(Throwable onError) {
        onError.printStackTrace();
        view.hideLoading();
        view.showErrorLoadingCollection(onError.getMessage());
    }

    @Override
    public void init(MyCollectionView view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unLoad() {
        compositeDisposable.dispose();
        view = null;

    }
}
