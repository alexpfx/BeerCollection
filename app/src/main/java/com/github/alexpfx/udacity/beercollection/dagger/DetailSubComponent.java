package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.detail.DetailFragment;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {DetailModule.class, BeerModule.class})
public interface DetailSubComponent {

    //    void inject (DetailActivity activity);
    void inject(DetailFragment fragment);
}
