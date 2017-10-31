package com.github.alexpfx.udacity.beercollection.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by alexandre on 28/10/17.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseScope {

}
