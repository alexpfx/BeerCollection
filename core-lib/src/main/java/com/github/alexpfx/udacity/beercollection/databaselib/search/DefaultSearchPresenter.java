package com.github.alexpfx.udacity.beercollection.databaselib.search;

import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.databaselib.util.Mappers;
import com.github.alexpfx.udacity.beercollection.databaselib.util.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@PerActivity
public class DefaultSearchPresenter implements SearchPresenter {


    private final SearchInteractor searchInteractor;
    private SearchView searchView;
    private SchedulerProvider schedulerProvider;
    private Disposable disposable;


    @Inject
    public DefaultSearchPresenter(SearchInteractor searchInteractor, SchedulerProvider
            schedulerProvider) {
        this.searchInteractor = searchInteractor;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void search(String query) {
        searchView.clearResults();
        searchView.showLoading();

        disposable = searchInteractor.searchBeers(query).cache().toFlowable().flatMapIterable(list -> list)
                .filter(Mappers.BEER_FILTER)
                .take(Constants.DEFAULT_MAX_SEARCH_RESULTS)
                .toList()
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(
                        beerListData -> {
                            List<Beer> data = beerListData;
                            searchView.hideLoading();
                            if (data == null || data.isEmpty()) {
                                searchView.showNoResults(query);
                            } else {
                                searchView.showSearchResult(data);
                            }
                        },
                        error -> searchView.showSearchError()
                );


    }


    @Override
    public void unLoad() {
        if (disposable != null)
            disposable.dispose();

        searchView = null;

    }

    @Override
    public void init(SearchView view) {
        this.searchView = view;
    }
}
