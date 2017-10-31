package com.github.alexpfx.udacity.beercollection.beer.search;

import com.github.alexpfx.udacity.beercollection.base.BaseView;
import com.github.alexpfx.udacity.beercollection.domain.model.local.Beer;

import java.util.List;

/**
 * Created by alexandre on 17/10/17.
 */

public interface SearchView extends BaseView {
    void showSearchResult(List<Beer> searchResult);

    void showSearchError (Throwable throwable);

    void showNoResults(String query);
}
