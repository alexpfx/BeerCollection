package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.databaselib.search.DefaultSearchInteractor;
import com.github.alexpfx.udacity.beercollection.databaselib.search.DefaultSearchPresenter;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchInteractor;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchPresenter;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchView;

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
