package com.github.alexpfx.udacity.beercollection;

import java.util.List;


public interface BaseAdapter<I> {
    void setItems(List<I> items);

    void clear();

    I getItem(int position);
}
