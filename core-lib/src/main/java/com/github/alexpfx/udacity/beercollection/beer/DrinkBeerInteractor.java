package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BaseInteractor;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;

import io.reactivex.Single;


public interface DrinkBeerInteractor extends BaseInteractor {
    Single save (DrinkBeerUpdateItem beer);
}
