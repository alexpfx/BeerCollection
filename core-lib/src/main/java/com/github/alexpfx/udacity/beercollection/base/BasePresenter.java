package com.github.alexpfx.udacity.beercollection.base;

/**
 * Created by alexandre on 14/10/17.
 */

public interface BasePresenter<V extends BaseView> {
    void onDestroy ();
    void bind (V view);

}
