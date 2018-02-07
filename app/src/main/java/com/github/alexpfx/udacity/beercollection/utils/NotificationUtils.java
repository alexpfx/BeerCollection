package com.github.alexpfx.udacity.beercollection.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.detail.DetailActivity;

public class NotificationUtils {

    private static final int BEER_INFO_UPDATES_PENDING_INTENT_ID = 1001;

    private static final String CACHE_CLEANER_INFO_CHANNEL_ID = "notification";

    private static final int BEER_INFO_UPDATES_NOTIFICATION_ID = 1001;


    public static void showInfoAboutCacheCleanerProcess(Context context, int count) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context
                .NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CACHE_CLEANER_INFO_CHANNEL_ID, context
                    .getString(R.string
                            .main_notification_channel_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        String body = context.getString(R.string.message_cache_clared);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CACHE_CLEANER_INFO_CHANNEL_ID)
                        .setColor(ContextCompat.getColor(context, R.color.primary))
                        .setSmallIcon(R.drawable.ic_new_releases_white_24dp)
                        .setContentTitle(context.getResources().getQuantityString(R.plurals
                                .notification_title_cache_clared, count, count))
                        .setContentText(body)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setContentIntent(contentIntent(context));


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(BEER_INFO_UPDATES_NOTIFICATION_ID, builder.build());
    }


    private static PendingIntent contentIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        return PendingIntent.getActivity(context, BEER_INFO_UPDATES_PENDING_INTENT_ID, intent, PendingIntent
                .FLAG_UPDATE_CURRENT);
    }
}
