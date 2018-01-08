package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;

/**
 * Created by alexandre on 08/01/18.
 */

public interface CacheCleanerPresenter extends BasePresenter<CacheCleanerView> {
    void clearCache (long expirationTime);
}
