package com.github.alexpfx.udacity.beercollection.utils;

import android.text.TextUtils;


public final class TextViewUtils {

    private TextViewUtils() {
    }

    public static String valueOrDash(Object value) {
        if (TextUtils.isEmpty((CharSequence) value)) {
            return " - ";
        } else {
            return value.toString();
        }
    }


}
