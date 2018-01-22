package com.github.alexpfx.udacity.beercollection.utils;

/**
 * Created by alexandre on 21/01/18.
 */
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
