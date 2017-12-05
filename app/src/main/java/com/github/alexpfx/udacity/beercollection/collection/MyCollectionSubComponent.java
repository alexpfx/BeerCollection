package com.github.alexpfx.udacity.beercollection.collection;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.MyCollectionModule;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;

import dagger.Subcomponent;

/**
 * Created by alexandre on 10/11/17.
 */


@PerActivity
@Subcomponent(modules = {MyCollectionModule.class})
public interface MyCollectionSubComponent {
    void inject (MyCollectionActivity activity);
    void inject (MyCollectionFragment fragment);
}
