package com.github.alexpfx.udacity.beercollection;

import com.github.alexpfx.udacity.beercollection.domain.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexandre on 14/10/17.
 */

@Singleton
@Component(modules = {
        AndroidModule.class,
        ServiceModule.class

})
public interface ApplicationComponent {
    void inject (MainActivity mainActivity);
}
