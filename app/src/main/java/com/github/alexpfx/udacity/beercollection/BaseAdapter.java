package com.github.alexpfx.udacity.beercollection;

import java.util.List;

/**
 * Created by alexandre on 04/11/17.
 */

public interface BaseAdapter<I> {
    void setItems(List<I> items);
    void clear ();
    I getItem (int position);
}
