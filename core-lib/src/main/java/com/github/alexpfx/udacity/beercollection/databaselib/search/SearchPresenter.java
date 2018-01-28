package com.github.alexpfx.udacity.beercollection.databaselib.search;

import com.github.alexpfx.udacity.beercollection.base.BasePresenter;


public interface SearchPresenter extends BasePresenter <SearchView> {
    void search(String query);
}
