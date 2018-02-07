package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.databaselib.dagger.DatabaseModule;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.ServiceModule;
import com.github.alexpfx.udacity.beercollection.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {
        AndroidModule.class,
        ServiceModule.class,
        DatabaseModule.class
})
public interface ApplicationComponent {

    SearchSubComponent plus(SearchModule searchModule);

    DetailSubComponent plus(DetailModule detailModule);

    MyCollectionSubComponent plus(MyCollectionModule myCollectionModule);

    CacheCleanerSubComponent plus(CacheCleanerModule cacheCleanerModule);

    void inject(LoginActivity activity);
}
