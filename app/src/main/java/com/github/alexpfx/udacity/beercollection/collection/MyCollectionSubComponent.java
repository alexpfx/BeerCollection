package com.github.alexpfx.udacity.beercollection.collection;

import com.github.alexpfx.udacity.beercollection.dagger.MyCollectionModule;
import com.github.alexpfx.udacity.beercollection.dagger.MyCollectionScope;

import dagger.Subcomponent;

/**
 * Created by alexandre on 10/11/17.
 */


@MyCollectionScope
@Subcomponent(modules = {MyCollectionModule.class})
public interface MyCollectionSubComponent {
    void inject (MyCollectionFragment fragment);
}
