package com.github.alexpfx.udacity.beercollection.utils;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
