package com.github.alexpfx.udacity.beercollection.utils;

public class SelectableItem<T> {

    private T item;
    private boolean selected = false;

    public SelectableItem(T item, boolean selected) {
        this.item = item;
        this.selected = selected;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
