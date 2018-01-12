package com.github.alexpfx.udacity.beercollection.collection.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.collection.MyCollectionActivity;

public class CollectionWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_beers_widget);

        for (int i = 0; i < appWidgetIds.length; i++) {
            setupViews (context, appWidgetIds[i], remoteViews);
        }

        PendingIntent launchAppPI = createLaunchAppPendingIntent(context);

        UpdateWidgetIntentService.start(context);

        remoteViews.setRemoteAdapter(R.id.widget_list_collection, new Intent(context, CollectionRemoteViewService.class));

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);

        super.onUpdate(context, appWidgetManager, appWidgetIds);

    }

    private void setupViews(Context context, int appWidgetId, RemoteViews views) {


    }


    private PendingIntent createLaunchAppPendingIntent(Context context) {
        Intent intent = new Intent(context, MyCollectionActivity.class);
        return PendingIntent.getActivity(context, 0, intent, 0);
    }

}
