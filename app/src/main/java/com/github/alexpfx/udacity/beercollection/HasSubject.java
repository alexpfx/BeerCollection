package com.github.alexpfx.udacity.beercollection;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by alexandre on 26/11/17.
 */

public interface HasSubject <T> {
    PublishSubject<T> getSubject ();
}
