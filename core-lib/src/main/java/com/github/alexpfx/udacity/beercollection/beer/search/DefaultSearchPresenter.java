package com.github.alexpfx.udacity.beercollection.beer.search;

import com.github.alexpfx.udacity.beercollection.dagger.SearchScope;
import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.local.LocalType;
import com.github.alexpfx.udacity.beercollection.util.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by alexandre on 17/10/17.
 */

@SearchScope
public class DefaultSearchPresenter implements SearchPresenter {


    public static final String SORT = "ASC";
    public static final String ORDER = "name";
    private final SearchView searchView;
    private final SearchInteractor searchInteractor;
    private SchedulerProvider schedulerProvider;


    @Inject
    public DefaultSearchPresenter(SearchView searchView, SearchInteractor searchInteractor, SchedulerProvider
            schedulerProvider) {
        this.searchView = searchView;
        this.searchInteractor = searchInteractor;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void search(String query) {
        Single<LocalType<List<Beer>>> cached = searchInteractor.searchBeers(query);

        cached.subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        beerListData -> {
                            List<Beer> data = beerListData.getData();
                            if (data != null || data.isEmpty()){
                                searchView.showSearchResult(data);
                            }else{
                                searchView.showNoResults (query);
                            }

                        },
                        searchView::showSearchError

                );

    }
}
