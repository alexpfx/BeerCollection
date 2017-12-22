package com.github.alexpfx.udacity.beercollection.utils;

/**
 * Created by alexandre on 17/12/17.
 */

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T item);
}
