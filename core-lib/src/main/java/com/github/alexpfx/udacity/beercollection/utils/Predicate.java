package com.github.alexpfx.udacity.beercollection.utils;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T item);
}
