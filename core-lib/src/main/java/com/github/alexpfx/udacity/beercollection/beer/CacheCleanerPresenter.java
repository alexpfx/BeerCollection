package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;

public interface CacheCleanerPresenter extends BasePresenter<CacheCleanerView> {
    void clearCache (long expirationTime);
}
