package com.github.alexpfx.udacity.beercollection.beer.collection;

import com.github.alexpfx.udacity.beercollection.dagger.MyCollectionScope;
import com.github.alexpfx.udacity.beercollection.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by alexandre on 09/11/17.
 */
@MyCollectionScope
public class LoadCollectionPresenterImpl implements LoadCollectionPresenter {

    private MyCollectionView view;
    private final MyCollectionInteractor interactor;
    private final SchedulerProvider provider;
    private Disposable subscription;


    @Inject
    public LoadCollectionPresenterImpl(MyCollectionInteractor interactor, SchedulerProvider provider){
        this.interactor = interactor;
        this.provider = provider;
    }


    @Override
    public void load() {
        view.showLoading();
        subscription = interactor.load().observeOn(provider.io()).subscribeOn(provider.mainThread()).subscribe(
                resultList -> {
                    view.showUserCollection(resultList);
                    view.hideLodading();
                },
                error -> {
                    view.showErrorLoadingCollection();
                    view.hideLodading();
                }

        );

    }

    @Override
    public void onDestroy() {
        if (subscription != null){
            subscription.dispose();
        }


    }

    @Override
    public void bind(MyCollectionView view) {
        this.view = view;
    }
}
