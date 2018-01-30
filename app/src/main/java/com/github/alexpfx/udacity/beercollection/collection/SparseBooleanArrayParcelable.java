package com.github.alexpfx.udacity.beercollection.collection;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

/**
 * Created by alexandre on 28/01/18.
 */

public class SparseBooleanArrayParcelable implements Parcelable{

    private SparseBooleanArray sparseBooleanArray;


    public SparseBooleanArrayParcelable(SparseBooleanArray sparseBooleanArray) {
        this.sparseBooleanArray = sparseBooleanArray;
    }


    protected SparseBooleanArrayParcelable(Parcel in) {
        sparseBooleanArray = in.readSparseBooleanArray();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSparseBooleanArray(sparseBooleanArray);
    }


    @Override
    public int describeContents() {
        return 0;
    }


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


    public SparseBooleanArray getSparseBooleanArray() {
        return sparseBooleanArray;
    }


    public void setSparseBooleanArray(SparseBooleanArray sparseBooleanArray) {
        this.sparseBooleanArray = sparseBooleanArray;
    }
}
