package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BaseView;

/**
 * Created by alexandre on 12/11/17.
 */

public interface DrinkBeerView extends BaseView{
    void showDrinkAdded(int quantity);
    void refresh ();
    void showError(Object error);
}
