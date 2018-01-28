package com.github.alexpfx.udacity.beercollection.beer;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;
import com.github.alexpfx.udacity.beercollection.domain.model.DrinkBeerUpdateItem;


public interface DrinkBeerPresenter extends BasePresenter<DrinkBeerView> {
    DrinkBeerView EMPTY = new DrinkBeerView() {
        @Override
        public void showDrinkAdded(String beerId, int quantity) {

        }


        @Override
        public void showErrorOnDrinkBeer(Object error) {

        }
    };

    void drink(DrinkBeerUpdateItem item);
}
