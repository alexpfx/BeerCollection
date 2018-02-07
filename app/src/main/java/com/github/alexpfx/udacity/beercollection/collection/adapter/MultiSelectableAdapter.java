package com.github.alexpfx.udacity.beercollection.collection.adapter;

public interface MultiSelectableAdapter {

    boolean isSelectable();

    void setSelectable(boolean isSelectable);

    boolean isSelected(int position);

    void setSelected(int position, boolean isSelected);

    void clearSelections();
}
