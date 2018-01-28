package com.github.alexpfx.udacity.beercollection.beer.beer;

import com.github.alexpfx.udacity.beercollection.base.BaseView;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

public interface LoadBeerInfoPresenterView extends BaseView {
    void showBeerInfo(Beer beer);
    void showLoadError (Throwable throwable);
}
