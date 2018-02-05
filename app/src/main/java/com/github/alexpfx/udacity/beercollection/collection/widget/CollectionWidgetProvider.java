package com.github.alexpfx.udacity.beercollection.collection.widget;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.collection.MyCollectionActivity;
import com.github.alexpfx.udacity.beercollection.detail.DetailActivity;

public class CollectionWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.layout_widget_beers);

            PendingIntent piAppLaunch = getAppLaunchPendingIntent(context);
            rv.setOnClickPendingIntent(R.id.widget_image_logo, piAppLaunch);

            PendingIntent piItemClickTemplate = getItemListClickPendingIntent(context);
            rv.setPendingIntentTemplate(R.id.widget_list_collection, piItemClickTemplate);

            rv.setRemoteAdapter(R.id.widget_list_collection, new Intent(context, CollectionRemoteViewService
                    .class));

            UpdateWidgetIntentService.start(context);

            appWidgetManager.updateAppWidget(appWidgetId, rv);
        }
    }


    private PendingIntent getAppLaunchPendingIntent(Context context) {
        Intent intent = new Intent(context, MyCollectionActivity.class);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    private PendingIntent getItemListClickPendingIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);

        return TaskStackBuilder.create(context).addNextIntentWithParentStack(intent).getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
