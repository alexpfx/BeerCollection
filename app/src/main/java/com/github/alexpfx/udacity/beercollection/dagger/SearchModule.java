package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.beer.search.DefaultSearchInteractor;
import com.github.alexpfx.udacity.beercollection.beer.search.DefaultSearchPresenter;
import com.github.alexpfx.udacity.beercollection.beer.search.SearchInteractor;
import com.github.alexpfx.udacity.beercollection.beer.search.SearchPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 17/10/17.
 */

@Module
public class SearchModule {

    @Provides
    @SearchScope
    public SearchPresenter searchPresenter(DefaultSearchPresenter searchPresenter) {
        return searchPresenter;
    }

    @Provides
    @SearchScope
    public SearchInteractor searchInteractor(DefaultSearchInteractor searchInteractor) {
        return searchInteractor;
    }



}
