package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.detail.DetailActivity;

import dagger.Subcomponent;

/**
 * Created by alexandre on 04/11/17.
 */

@PerActivity
@Subcomponent(modules = DetailModule.class)
public interface DetailSubComponent {

    void inject (DetailActivity activity);
}
