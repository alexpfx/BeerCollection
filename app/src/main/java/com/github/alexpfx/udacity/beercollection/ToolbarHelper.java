package com.github.alexpfx.udacity.beercollection;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by alexandre on 21/11/17.
 */

public class ToolbarHelper {
    public static void setupToolbar(AppCompatActivity activity, Toolbar toolbar, boolean homeButtonEnabled, boolean
            displayHomeAsUp) {
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setHomeButtonEnabled(homeButtonEnabled);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUp);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }



}
