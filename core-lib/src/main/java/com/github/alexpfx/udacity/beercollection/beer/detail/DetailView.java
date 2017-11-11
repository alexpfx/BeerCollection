package com.github.alexpfx.udacity.beercollection.beer.detail;

import com.github.alexpfx.udacity.beercollection.base.BaseView;
import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;

/**
 * Created by alexandre on 04/11/17.
 */

public interface DetailView extends BaseView {
    void showBeer (Beer beer);
    void showLoadError (Throwable throwable);
}
