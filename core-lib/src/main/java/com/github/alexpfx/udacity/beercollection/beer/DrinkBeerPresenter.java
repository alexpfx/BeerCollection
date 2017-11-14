package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;

/**
 * Created by alexandre on 11/11/17.
 */

public interface DrinkBeerPresenter extends BasePresenter<DrinkBeerView>{
    void drink (DrinkBeerUpdateItem item);
}
