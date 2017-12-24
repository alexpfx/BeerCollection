package com.github.alexpfx.udacity.beercollection.databaselib.dagger;

import com.github.alexpfx.udacity.beercollection.LoginActivity;
import com.github.alexpfx.udacity.beercollection.PreferenceActivity;
import com.github.alexpfx.udacity.beercollection.collection.MyCollectionSubComponent;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        AndroidModule.class,
        ServiceModule.class,
        DatabaseModule.class

})
public interface ApplicationComponent {
    SearchSubComponent plus (SearchModule searchModule);
    DetailSubComponent plus (DetailModule detailModule);
    MyCollectionSubComponent plus (MyCollectionModule myCollectionModule, ActivityModule activityModule);

    void inject (LoginActivity activity);
    void inject (PreferenceActivity activity);

}
