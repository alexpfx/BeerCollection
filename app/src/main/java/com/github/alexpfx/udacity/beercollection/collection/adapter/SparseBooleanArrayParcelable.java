package com.github.alexpfx.udacity.beercollection.collection.adapter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

public class SparseBooleanArrayParcelable implements Parcelable {

    public static final Creator<SparseBooleanArrayParcelable> CREATOR = new Creator<SparseBooleanArrayParcelable>() {
        @Override
        public SparseBooleanArrayParcelable createFromParcel(Parcel in) {
            return new SparseBooleanArrayParcelable(in);
        }


        @Override
        public SparseBooleanArrayParcelable[] newArray(int size) {
            return new SparseBooleanArrayParcelable[size];
        }
    };

    private SparseBooleanArray sparseBooleanArray;


    public SparseBooleanArrayParcelable(SparseBooleanArray sparseBooleanArray) {
        this.sparseBooleanArray = sparseBooleanArray;
    }


    protected SparseBooleanArrayParcelable(Parcel in) {
        sparseBooleanArray = in.readSparseBooleanArray();
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSparseBooleanArray(sparseBooleanArray);
    }


    public SparseBooleanArray getSparseBooleanArray() {
        return sparseBooleanArray;
    }
}
