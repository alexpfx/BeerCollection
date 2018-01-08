package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.detail.DetailFragment;

import dagger.Subcomponent;

/**
 * Created by alexandre on 04/11/17.
 */

@PerActivity
@Subcomponent(modules = {DetailModule.class,  BeerModule.class})
public interface DetailSubComponent {

//    void inject (DetailActivity activity);
    void inject (DetailFragment fragment);
}
