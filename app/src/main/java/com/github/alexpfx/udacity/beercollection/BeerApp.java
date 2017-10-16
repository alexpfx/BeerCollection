package com.github.alexpfx.udacity.beercollection;

import android.app.Application;
import android.content.Context;

/**
 * Created by alexandre on 14/10/17.
 */

public class BeerApp extends Application implements HasComponent<ApplicationComponent> {

    ApplicationComponent applicationComponent;

    public static HasComponent<ApplicationComponent> get(Context context) {
        return get(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = createComponent();
    }

    private ApplicationComponent createComponent() {
        return DaggerApplicationComponent.create();
    }

    @Override
    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
