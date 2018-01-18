package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BaseView;

/**
 * Created by alexandre on 08/01/18.
 */

public interface CacheCleanerView extends BaseView {
    void whenClearCacheRoutineStarts();

    void whenClearCacheRoutineEnds(int removed);
}
