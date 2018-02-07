package com.github.alexpfx.udacity.beercollection.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class SelectableItem<T> implements Parcelable {

    public static final Creator<SelectableItem> CREATOR = new Creator<SelectableItem>() {
        @Override
        public SelectableItem createFromParcel(Parcel in) {
            return new SelectableItem(in);
        }


        @Override
        public SelectableItem[] newArray(int size) {
            return new SelectableItem[size];
        }
    };

    private T item;

    private boolean selected = false;


    public SelectableItem(T item) {
        this.item = item;
    }


    protected SelectableItem(Parcel in) {
        selected = in.readByte() != 0;
    }


    public static <T> SelectableItem createFrom(T item) {
        return new SelectableItem<>(item);
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (selected ? 1 : 0));
    }


    public T getItem() {
        return item;
    }


    public boolean isSelected() {
        return selected;
    }


    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
