package com.github.alexpfx.udacity.beercollection.collection;

/**
 * Created by alexandre on 26/12/17.
 */

public interface SelectableAdapter {


    void setItemChecked(int position, boolean isChecked);

    boolean isItemChecked(int position);

    boolean isSelectable();

    void setSelectable(boolean selectable);

    boolean hasSelection ();


}
