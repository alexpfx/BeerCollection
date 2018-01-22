package com.github.alexpfx.udacity.beercollection.collection;

import android.support.v7.util.DiffUtil;

import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;

import java.util.List;

/**
 * Created by alexandre on 21/01/18.
 */

class CollectionDiffCalback extends DiffUtil.Callback {
    private final List<CollectionItem> oldItems;
    private final List<CollectionItem> newItems;


    public CollectionDiffCalback(List<CollectionItem> newItems, List<CollectionItem> oldItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).getBeer().getId().equals(newItems.get(newItemPosition).getBeer().getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).equals(newItems.get(newItemPosition));
    }
}
