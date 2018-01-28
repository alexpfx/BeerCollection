package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BaseView;


public interface DrinkBeerView extends BaseView{
    void showDrinkAdded(String beerId, int quantity);
    void showErrorOnDrinkBeer(Object error);
}
