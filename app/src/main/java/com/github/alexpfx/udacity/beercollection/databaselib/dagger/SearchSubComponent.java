package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.search.SearchActivity;

import dagger.Subcomponent;

/**
 * Created by alexandre on 17/10/17.
 */

@PerActivity
@Subcomponent(modules = {SearchModule.class, ActivityModule.class})
public interface SearchSubComponent {
    void inject (SearchActivity mainActivity);
}
