package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BaseInteractor;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;

import io.reactivex.Single;

/**
 * Created by alexandre on 12/11/17.
 */

public interface DrinkBeerInteractor extends BaseInteractor {
    Single save (DrinkBeerUpdateItem beer);
}
