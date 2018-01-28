package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BaseView;


public interface CacheCleanerView extends BaseView {
    void whenClearCacheRoutineStarts();

    void whenClearCacheRoutineEnds(int removed);
}
