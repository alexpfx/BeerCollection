package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BaseInteractor;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;

/**
 * Created by alexandre on 12/11/17.
 */

public interface DrinkBeerInteractor extends BaseInteractor {
    void save (DrinkBeerUpdateItem beer);
}
