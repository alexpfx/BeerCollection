package com.github.alexpfx.udacity.beercollection.collection.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.collection.MyCollectionActivity;
import com.github.alexpfx.udacity.beercollection.detail.DetailActivity;

public class CollectionWidgetProvider extends AppWidgetProvider {
    public static final String ACTION_VIEW_DETAILS = "ACTION_VIEW_DETAILS";



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_beers_widget);

        setupViews(context, remoteViews);

        PendingIntent launchAppPI = createLaunchAppPendingIntent(context);
        remoteViews.setOnClickPendingIntent(R.id.widget_image_logo, launchAppPI);

        UpdateWidgetIntentService.start(context);

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        super.onUpdate(context, appWidgetManager, appWidgetIds);

    }

    private PendingIntent createIntentTemplate(Context context) {
        Intent selfIntent = new Intent(context, CollectionWidgetProvider.class);
        selfIntent.setAction(ACTION_VIEW_DETAILS);
        return PendingIntent.getBroadcast(context, 0, selfIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void setupViews(Context context, RemoteViews remoteViews) {
        remoteViews.setRemoteAdapter(R.id.widget_list_collection, new Intent(context, CollectionRemoteViewService
                .class));

        PendingIntent clickIntentTemplate = createIntentTemplate (context);
        remoteViews.setPendingIntentTemplate(R.id.widget_list_collection, clickIntentTemplate);
    }

    private static final String TAG = "CollectionWidgetProvide";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: "+intent.getAction());
        if (ACTION_VIEW_DETAILS.equals(intent.getAction())) {
            String beerId = intent.getStringExtra(Constants.KEY_BEER_ID);
            Intent itDetail = new Intent(context, DetailActivity.class);
            itDetail.putExtra(Constants.KEY_BEER_ID, beerId);
            Log.d(TAG, "onReceive: "+beerId);
            context.startActivity(itDetail);
        }
        super.onReceive(context, intent);

    }

    private PendingIntent createLaunchAppPendingIntent(Context context) {
        Intent intent = new Intent(context, MyCollectionActivity.class);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
