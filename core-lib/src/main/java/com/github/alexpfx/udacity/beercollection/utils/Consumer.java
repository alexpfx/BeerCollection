package com.github.alexpfx.udacity.beercollection.utils;


@FunctionalInterface
public interface Consumer<T> {
    void accept (T param);
}
