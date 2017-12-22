package com.github.alexpfx.udacity.beercollection.base;


public interface BasePresenter<V extends BaseView> {
    void onDestroy ();
    void bind (V view);

}
