package com.github.alexpfx.udacity.beercollection.beer.search;

import com.github.alexpfx.udacity.beercollection.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.util.Mappers;
import com.github.alexpfx.udacity.beercollection.util.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by alexandre on 17/10/17.
 */

@PerActivity
public class DefaultSearchPresenter implements SearchPresenter {


    public static final String SORT = "ASC";
    public static final String ORDER = "name";
    private final SearchInteractor searchInteractor;
    private SearchView searchView;
    private SchedulerProvider schedulerProvider;
    private Disposable disposable;


    @Inject
    public DefaultSearchPresenter(SearchView searchView, SearchInteractor searchInteractor, SchedulerProvider
            schedulerProvider) {
        this.searchView = searchView;
        this.searchInteractor = searchInteractor;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void search(String query) {
        searchView.clearResults();
        searchView.showLoading();

        disposable = searchInteractor.searchBeers(query).cache().toFlowable().flatMapIterable(list -> list)
                .filter(Mappers.BEER_FILTER)
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
    public void onDestroy() {
        if (disposable != null)
            disposable.dispose();

    }

    @Override
    public void bind(SearchView view) {

    }
}
