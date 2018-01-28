package com.github.alexpfx.udacity.beercollection.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ToolbarUtils {
    public static void setupToolbar(AppCompatActivity activity, Toolbar toolbar, boolean homeButtonEnabled, boolean
            displayHomeAsUp, boolean showTitleEnabled, boolean showHomeEnabled) {
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(showHomeEnabled);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUp);
        activity.getSupportActionBar().setHomeButtonEnabled(homeButtonEnabled);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(showTitleEnabled);

    }
}
