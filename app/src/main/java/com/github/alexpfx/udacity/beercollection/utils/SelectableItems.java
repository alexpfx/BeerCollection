package com.github.alexpfx.udacity.beercollection.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


public class SelectableItems implements SelectableAdapter, Iterable<Boolean> {

    public static final Parcelable.Creator<SelectableItems> CREATOR = new Parcelable.Creator<SelectableItems>() {
        @Override
        public SelectableItems createFromParcel(Parcel source) {
            return new SelectableItems(source);
        }

        @Override
        public SelectableItems[] newArray(int size) {
            return new SelectableItems[size];
        }
    };
    private SparseBooleanArray selectedPositions = new SparseBooleanArray();

    protected SelectableItems(Parcel in) {
        this.selectedPositions = in.readSparseBooleanArray();
    }

    public SelectableItems() {
    }

    @Override
    public void setItemChecked(int position, boolean isChecked) {
        selectedPositions.put(position, isChecked);
    }

    @Override
    public boolean isItemChecked(int position) {
        return selectedPositions.get(position);
    }

    @Override
    public void clearSelectedItems() {
        selectedPositions.clear();
    }



    @Override
    public boolean hasSelection() {
        for (int i = 0; i < selectedPositions.size(); i++) {
            int key = selectedPositions.keyAt(i);
            if (selectedPositions.get(key)) {
                return true;
            }
        }

        return false;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSparseBooleanArray(this.selectedPositions);
    }


    @NonNull
    @Override
    public Iterator<Boolean> iterator() {
        return new Iterator<Boolean>() {
            private int curretIndex = 0;

            @Override
            public boolean hasNext() {
                return curretIndex < selectedPositions.size();
            }

            @Override
            public Boolean next() {
                int keyAt = selectedPositions.keyAt(curretIndex);
                return selectedPositions.get(keyAt);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Boolean> action) {
        throw new UnsupportedOperationException("forEach not supported");
    }

    @Override
    public Spliterator<Boolean> spliterator() {
        throw new UnsupportedOperationException("spliterator not supported");

    }
}
