package com.github.alexpfx.udacity.beercollection;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.beercollection.utils.Predicate;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractBaseAdapter<VH extends RecyclerView.ViewHolder, I> extends RecyclerView
        .Adapter<VH> implements BaseAdapter<I> {

    private List<I> items;


    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate(LayoutInflater.from(parent.getContext()), parent);
        VH viewHolder = createViewHolder(view);
        return viewHolder;
    }

    protected abstract VH createViewHolder(View view);

    protected abstract View inflate(LayoutInflater inflater, ViewGroup parent);


    @Override
    public void setItems(List<I> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<I> filter (Predicate<I> predicate){

        List<I> filtered = new ArrayList<>();
        for (I item : items) {
            if (predicate.test(item)){
                filtered.add(item);
            }
        }
        return filtered;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public void clear() {
        if (items != null) {
            items.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public I getItem(int position) {
        if (items == null) {
            return null;
        }

        return items.get(position);
    }
}
