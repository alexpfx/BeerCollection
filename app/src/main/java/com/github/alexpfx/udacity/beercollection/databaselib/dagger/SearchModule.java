package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import android.app.Activity;

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

@Module(includes = ActivityModule.class)
public class SearchModule {


    public SearchModule() {

    }

    @Provides
    @PerActivity
    public SearchView searchView(Activity activity) {
        return (SearchView) activity;
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
