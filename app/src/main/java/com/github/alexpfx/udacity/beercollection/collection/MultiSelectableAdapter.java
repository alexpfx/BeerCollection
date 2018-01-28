package com.github.alexpfx.udacity.beercollection.collection;

/**
 * Created by alexandre on 21/01/18.
 */

public interface MultiSelectableAdapter {

    boolean isSelectable();

    void setSelectable(boolean isSelectable);

    boolean isSelected(int position);

    void setSelected(int position, boolean isSelected);

    void clearSelections();


}
