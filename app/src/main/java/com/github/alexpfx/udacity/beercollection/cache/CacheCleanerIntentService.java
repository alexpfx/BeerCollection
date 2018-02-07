package com.github.alexpfx.udacity.beercollection.cache;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.github.alexpfx.udacity.beercollection.BaseIntentService;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.CacheCleanerPresenter;
import com.github.alexpfx.udacity.beercollection.beer.CacheCleanerView;
import com.github.alexpfx.udacity.beercollection.utils.NotificationUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Classe responsável por apagar do banco de dados itens de cache que estão armazenados há mais de 24 horas.
 */
public class CacheCleanerIntentService extends BaseIntentService implements CacheCleanerView {

    public static final String ACTION_CLEAN_OLD_CACHE_DATA = "clean-old-cache-data";

    @Inject
    CacheCleanerPresenter presenter;


    public CacheCleanerIntentService() {
        super("CacheCleanerIntentService");
    }


    @Override
    public void whenClearCacheRoutineStarts() {
    }


    @Override
    public void whenClearCacheRoutineEnds(int removed) {
        if (removed == 0) {
            return;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences
                (getApplicationContext());
        boolean shouldShowNotification = defaultSharedPreferences.getBoolean(getString(R.string
                .pref_key_send_notification_when_cache_cleared_key), false);


        if (shouldShowNotification) {
            NotificationUtils.showInfoAboutCacheCleanerProcess(getApplicationContext(), removed);
        }
    }


    @Override
    public void injectDependencies(BeerApp app) {
        app.getCacheCleanerSubComponent().inject(this);
        presenter.init(this);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (Objects.equals(action, ACTION_CLEAN_OLD_CACHE_DATA)) {
            cleanUpCache();
        }
    }


    private void cleanUpCache() {
        presenter.clearCache(TimeUnit.HOURS.toMillis(Constants.CACHE_EXPIRATION));
    }
}

