package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.SearchActivity;

import dagger.Subcomponent;

/**
 * Created by alexandre on 17/10/17.
 */

@SearchScope
@Subcomponent(modules = {SearchModule.class, ActivityModule.class})
public interface SearchSubComponent {
    void inject (SearchActivity mainActivity);
}
