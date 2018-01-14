package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.search.DefaultSearchInteractor;
import com.github.alexpfx.udacity.beercollection.databaselib.search.DefaultSearchPresenter;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchInteractor;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 17/10/17.
 */

@Module()
public class SearchModule {

    public SearchModule() {

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
