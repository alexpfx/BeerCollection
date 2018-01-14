package com.github.alexpfx.udacity.beercollection.beer.beer;

import com.github.alexpfx.udacity.beercollection.base.BaseView;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

/**
 * Created by alexandre on 04/11/17.
 */

public interface LoadBeerInfoPresenterView extends BaseView {
    void showBeerInfo(Beer beer);
    void showLoadError (Throwable throwable);
}
