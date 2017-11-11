package com.github.alexpfx.udacity.beercollection.beer.detail;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;

/**
 * Created by alexandre on 04/11/17.
 */

public interface DetailPresenter extends BasePresenter<DetailView> {
    void load(String beerId);
}
