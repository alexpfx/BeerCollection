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

    /*
    public static void setOrHide(String label, TextView view, Object o) {
        if (o == null) {
            view.setVisibility(View.GONE);
        } else {
            CharSequence text = TextUtils.concat(label, ": ", o.toString());
            view.setText(text);
        }
    }
    */

}
