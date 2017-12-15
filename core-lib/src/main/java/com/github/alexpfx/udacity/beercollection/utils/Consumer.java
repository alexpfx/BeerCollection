package com.github.alexpfx.udacity.beercollection.utils;

/**
 * Created by alexandre on 12/12/17.
 */

@FunctionalInterface
public interface Consumer<T> {
    void accept (T param);
}
