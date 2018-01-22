package com.github.alexpfx.udacity.beercollection.collection;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by alexandre on 21/01/18.
 */


public class CollectionMultiselectableAdapter extends RecyclerView.Adapter<CollectionViewHolder> implements
        MultiSelectableAdapter {

    private final PublishSubject<View> detailClickSubject = PublishSubject.create();
    private final PublishSubject<View> addBeerClickSubject = PublishSubject.create();
    private final PublishSubject<View> clickItemViewSubject = PublishSubject.create();
    private final PublishSubject<View> longClickItemViewSubject = PublishSubject.create();
    private SparseBooleanArray selectControl = new SparseBooleanArray();
    private boolean selectable;
    private List<CollectionItem> items = new ArrayList<>();
    @Inject
    public CollectionMultiselectableAdapter() {
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        return new CollectionViewHolder(view, detailClickSubject, addBeerClickSubject,
                clickItemViewSubject, longClickItemViewSubject);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        CollectionItem item = items.get(position);
        holder.bind(item, selectControl.get(position), isSelectable());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public boolean isSelectable() {
        return selectable;
    }

    @Override
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
        this.selectControl.clear();
    }

    @Override
    public boolean isSelected(int position) {
        return selectControl.get(position);
    }

    @Override
    public void setSelected(int position, boolean isSelected) {
        selectControl.put(position, isSelected);
    }

    public CollectionItem getItemAt(int position) {
        return items.get(0);
    }

    public List<Integer> getSelectedPositions() {
        List<Integer> s = new ArrayList<>();
        int i = 0;
        do {
            if (selectControl.valueAt(i)) {
                int key = selectControl.keyAt(i);
                s.add(key);
            }
            i++;
        } while (i < items.size());
        return s;
    }

    public void deleteItems(List<Integer> selectedItemIds) {
        items.removeAll(selectedItemIds);
        swapItems(items);
    }

    public void swapItems(List<CollectionItem> newItems) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new CollectionDiffCalback(newItems, this.items));
        this.items.addAll(newItems);
        selectControl.clear();
        diffResult.dispatchUpdatesTo(this);
    }

    public Observable<View> getDetailClickObservable() {
        return detailClickSubject.hide();
    }

    public Observable<View> getAddBeerClickObservable() {
        return addBeerClickSubject.hide();
    }

    public Observable<View> getClickItemViewObservable() {
        return clickItemViewSubject.hide();
    }

    public Observable<View> getLongClickItemViewObservable() {
        return longClickItemViewSubject.hide();
    }
}
