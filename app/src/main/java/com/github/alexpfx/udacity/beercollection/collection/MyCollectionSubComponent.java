package com.github.alexpfx.udacity.beercollection.collection;

import com.github.alexpfx.udacity.beercollection.dagger.BeerModule;
import com.github.alexpfx.udacity.beercollection.dagger.DrinkBeerModule;
import com.github.alexpfx.udacity.beercollection.dagger.MyCollectionModule;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.preference.PreferenceFragment;

import dagger.Subcomponent;

/**
 * Created by alexandre on 10/11/17.
 */


@PerActivity
@Subcomponent(modules = {MyCollectionModule.class, DrinkBeerModule.class, BeerModule.class})
public interface MyCollectionSubComponent {
    void inject(MyCollectionActivity activity);

    void inject(MyCollectionFragment fragment);

    void inject(PreferenceFragment fragment);
}
