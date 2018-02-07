package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.collection.MyCollectionActivity;
import com.github.alexpfx.udacity.beercollection.collection.MyCollectionFragment;
import com.github.alexpfx.udacity.beercollection.collection.widget.CollectionRemoteViewFactory;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.preference.PreferenceFragment;

import dagger.Subcomponent;


@PerActivity
@Subcomponent(modules = {MyCollectionModule.class, DrinkBeerModule.class, BeerModule.class})
public interface MyCollectionSubComponent {

    void inject(MyCollectionActivity activity);

    void inject(MyCollectionFragment fragment);

    void inject(PreferenceFragment fragment);

    void inject(CollectionRemoteViewFactory factory);
}
