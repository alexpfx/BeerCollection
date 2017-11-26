package com.github.alexpfx.udacity.beercollection;

/**
 * Created by alexandre on 25/11/17.
 */

public interface Logger {
    void d (String message, Object... param);
    void e (Throwable throwable);
}
