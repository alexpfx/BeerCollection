package com.github.alexpfx.udacity.beercollection.utils;

import android.os.Parcelable;

/**
 * Created by alexandre on 26/12/17.
 */

public interface SelectableAdapter extends Parcelable, Iterable<Boolean> {


    void setItemChecked(int position, boolean isChecked);

    boolean isItemChecked(int position);

    boolean hasSelection();

    void clearSelectedItems();


}
