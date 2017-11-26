package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.search.DefaultSearchInteractor;
import com.github.alexpfx.udacity.beercollection.beer.search.DefaultSearchPresenter;
import com.github.alexpfx.udacity.beercollection.beer.search.SearchInteractor;
import com.github.alexpfx.udacity.beercollection.beer.search.SearchPresenter;
import com.github.alexpfx.udacity.beercollection.beer.search.SearchView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 17/10/17.
 */

@Module
public class SearchModule {

    private SearchView searchView;

    public SearchModule(SearchView searchView) {
        this.searchView = searchView;
    }


    @Provides
    @PerActivity
    public SearchView searchView() {
        return this.searchView;
    }

    @Provides
    @PerActivity
    public SearchPresenter searchPresenter(DefaultSearchPresenter searchPresenter) {
        return searchPresenter;
    }

    @Provides
    @PerActivity
    public SearchInteractor searchInteractor(DefaultSearchInteractor searchInteractor) {
        return searchInteractor;
    }



}
