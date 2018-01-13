package com.github.alexpfx.udacity.beercollection.beer.detail;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;


public interface LoadBeerInfoPresenter extends BasePresenter<LoadBeerInfoPresenterView> {
    void load(String beerId);
}
