package com.github.alexpfx.udacity.beercollection.beer.auth;

import com.github.alexpfx.udacity.beercollection.base.BaseView;

/**
 * Created by alexandre on 28/10/17.
 */

public interface LoginView extends BaseView {
    void showLoginSuccessful ();
    void showLoginCancelled ();
    void showLoginUnsuccessfull ();
}
