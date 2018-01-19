package com.github.alexpfx.udacity.beercollection.collection.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.github.alexpfx.udacity.beercollection.R;

/**
 * Created by alexandre on 11/01/18.
 */

public class UpdateWidgetIntentService extends IntentService {


    private static final String ACTION_UPDATE_COLLECTION = "ACTION_UPDATE_COLLECTION";

    public UpdateWidgetIntentService() {
        super("UpdateWidgetIntentService");
    }


    public static void start (Context context){
        Intent intent = new Intent(context, UpdateWidgetIntentService.class);
        intent.setAction(ACTION_UPDATE_COLLECTION);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, CollectionWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list_collection);

    }
}
