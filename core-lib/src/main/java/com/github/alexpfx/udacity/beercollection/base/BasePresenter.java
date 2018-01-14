package com.github.alexpfx.udacity.beercollection.base;


public interface BasePresenter<V extends BaseView> {
    void init(V view);
    void unLoad();

}
