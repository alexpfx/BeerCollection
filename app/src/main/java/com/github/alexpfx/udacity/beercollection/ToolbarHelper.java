package com.github.alexpfx.udacity.beercollection;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by alexandre on 21/11/17.
 */

public class ToolbarHelper {
    public static void setupToolbar(AppCompatActivity activity, Toolbar toolbar, boolean homeButtonEnabled, boolean
            displayHomeAsUp, boolean showTitleEnabled, boolean showHomeEnabled) {
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(showHomeEnabled);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUp);
        activity.getSupportActionBar().setHomeButtonEnabled(homeButtonEnabled);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(showTitleEnabled);

    }


    public static void changeToolbarAttributes(AppCompatActivity activity, boolean homeButtonEnabled, boolean
            displayHomeAsUp) {

        activity.getSupportActionBar().setHomeButtonEnabled(homeButtonEnabled);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUp);
    }


}
