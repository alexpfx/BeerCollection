package com.github.alexpfx.udacity.beercollection;

import android.content.Intent;
import android.util.Log;

import com.github.alexpfx.udacity.beercollection.beer.CacheCleanerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.CacheCleanerView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;


public class CacheCleanerIntentService extends BaseIntentService implements CacheCleanerView{
    public static final String ACTION_CLEAN_OLD_CACHE_DATA = "clean-old-cache-data";

    private static final String TAG = "CacheCleanerIntentServi";

    @Inject
    CacheCleanerPresenter presenter;


    public CacheCleanerIntentService() {
        super("CacheCleanerIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (action.equals(ACTION_CLEAN_OLD_CACHE_DATA)){
            cleanUpCache ();
        }

    }

    private void cleanUpCache() {
        presenter.clearCache(TimeUnit.DAYS.toMillis(1));
    }


    @Override
    public void showCacheCleanerStarted() {
        Log.d(TAG, "showCacheCleanerStarted: ");
    }

    @Override
    public void showCacheWasCleanedUp() {
        Log.d(TAG, "showCacheWasCleanedUp: ");
    }


    @Override
    public void injectDependencies(BeerApp app) {


    }
}

