package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.search.SearchActivity;
import com.github.alexpfx.udacity.beercollection.search.SearchFragment;

import dagger.Subcomponent;

/**
 * Created by alexandre on 17/10/17.
 */

@PerActivity
@Subcomponent(modules = {SearchModule.class, DrinkBeerModule.class, BeerModule.class})
public interface SearchSubComponent {
    void inject (SearchActivity mainActivity);
    void inject (SearchFragment searchFragment);
}
