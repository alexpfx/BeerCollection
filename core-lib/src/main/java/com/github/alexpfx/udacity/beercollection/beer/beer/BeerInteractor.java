package com.github.alexpfx.udacity.beercollection.beer.beer;

import com.github.alexpfx.udacity.beercollection.base.BaseInteractor;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by alexandre on 04/11/17.
 */

public interface BeerInteractor extends BaseInteractor {
    Flowable<Beer> load(String beerId);
    Single<Void> clearCache (long elapsedTime);
}
