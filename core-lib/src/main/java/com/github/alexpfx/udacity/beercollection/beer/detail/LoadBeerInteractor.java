package com.github.alexpfx.udacity.beercollection.beer.detail;

import com.github.alexpfx.udacity.beercollection.base.BaseInteractor;
import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.local.LocalType;

import io.reactivex.Flowable;

/**
 * Created by alexandre on 04/11/17.
 */

public interface LoadBeerInteractor extends BaseInteractor {
    Flowable<LocalType<Beer>> load(String beerId);
}
