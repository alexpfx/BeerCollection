package com.github.alexpfx.udacity.beercollection.collection;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.github.alexpfx.udacity.beercollection.utils.Function;
import com.github.alexpfx.udacity.beercollection.utils.Predicate;
import com.github.alexpfx.udacity.beercollection.utils.SelectableItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by alexandre on 21/01/18.
 */
@PerActivity
public class SelectableItemsAdapter extends RecyclerView.Adapter<CollectionViewHolder> implements
        MultiSelectableAdapter {

    private final PublishSubject<View> detailEvent = PublishSubject.create();
    private final PublishSubject<View> addBeerEvent = PublishSubject.create();
    private final PublishSubject<View> toggleSelectionEvent = PublishSubject.create();
    private final PublishSubject<View> toggleSelectionModeEvent = PublishSubject.create();
    private final List<SelectableItem<CollectionItem>> items = new ArrayList<>();
    private boolean selectable = false;
    private io.reactivex.functions.Predicate<View> isNotSeletionMode = view -> !selectable;
    private io.reactivex.functions.Predicate<View> isSeletionMode = view -> selectable;

    @Inject
    public SelectableItemsAdapter() {
    }

    @Override
    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    @Override
    public boolean isSelected(int position) {
        if (position == RecyclerView.NO_POSITION) {
            return false;
        }
        return items.get(position).isSelected();
    }

    @Override
    public void setSelected(int position, boolean isSelected) {
        items.get(position).setSelected(isSelected);
        notifyItemChanged(position);
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);

        return new CollectionViewHolder(view, detailEvent, addBeerEvent,
                toggleSelectionEvent, toggleSelectionModeEvent);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        SelectableItem<CollectionItem> item = items.get(position);
        holder.bind(item.getItem(), item.isSelected(), selectable);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void resetState(List<CollectionItem> newItems) {
        List<SelectableItem<CollectionItem>> sNewItems = new ArrayList<>();
        setSelectable(false);
        for (CollectionItem newItem : newItems) {
            sNewItems.add(SelectableItem.createFrom(newItem));
        }
        swapItems(sNewItems);
    }

    public void swapItems(List<SelectableItem<CollectionItem>> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }


    public <R> List<R> get(Predicate<SelectableItem<CollectionItem>>
                                   predicate, Function<SelectableItem<CollectionItem>, R> function) {
        List<R> list = new ArrayList<>();
        for (SelectableItem<CollectionItem> item : items) {
            if (predicate.test(item)) {
                list.add(function.apply(item));
            }
        }
        return list;
    }


    public Observable<View> getDetailEventObservable() {
        return detailEvent
                /*Discard view events if adapter state is in selection mode*/
                .filter(isNotSeletionMode)
                .hide();
    }

    public Observable<View> getAddBeerEventObservable() {
        return addBeerEvent
                .hide();
    }


    public Observable<View> getToggleSelectionEventObservable() {
        return toggleSelectionEvent
                .filter(isSeletionMode)
                .hide();
    }

    public Observable<View> getToggleSelectionModeEventObservable() {
        return toggleSelectionModeEvent.hide();
    }

}
