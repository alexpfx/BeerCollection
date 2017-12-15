package com.github.alexpfx.udacity.beercollection.databaselib.search;

import com.github.alexpfx.udacity.beercollection.base.BaseView;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import java.util.List;

/**
 * Created by alexandre on 17/10/17.
 */

public interface SearchView extends BaseView {
    void showSearchResult(List<Beer> searchResult);

    void showSearchError ();

    void showNoResults(String query);

    void showLoading ();

    void hideLoading ();

    void clearResults ();
}