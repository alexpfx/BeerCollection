package com.github.alexpfx.udacity.beercollection;

import android.app.IntentService;

/**
 * Created by alexandre on 08/01/18.
 */

public abstract class BaseIntentService extends IntentService {

    public BaseIntentService(String name) {
        super(name);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies((BeerApp) getApplicationContext());
    }

    public abstract void injectDependencies (BeerApp app);

}
