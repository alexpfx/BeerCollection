package com.github.alexpfx.udacity.beercollection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alexandre on 15/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HasComponent<ApplicationComponent> componentHolder = (HasComponent<ApplicationComponent>)
                getApplicationContext();

        injectDependencies(componentHolder.getComponent());
    }

    protected abstract void injectDependencies(ApplicationComponent component);

}
