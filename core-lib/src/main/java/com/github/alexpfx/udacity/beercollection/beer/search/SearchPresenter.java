package com.github.alexpfx.udacity.beercollection.beer.search;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;

/**
 * Created by alexandre on 17/10/17.
 */

public interface SearchPresenter extends BasePresenter<SearchView> {
    void search(String query);
}
