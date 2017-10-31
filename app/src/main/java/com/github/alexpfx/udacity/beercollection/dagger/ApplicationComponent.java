package com.github.alexpfx.udacity.beercollection.dagger;

import com.github.alexpfx.udacity.beercollection.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexandre on 14/10/17.
 */

@Singleton
@Component(modules = {
        AndroidModule.class,
        ServiceModule.class,
        DatabaseModule.class

})
public interface ApplicationComponent {
    SearchSubComponent plus (SearchModule searchModule, ActivityModule activityModule);

    void inject (LoginActivity activity);

}
