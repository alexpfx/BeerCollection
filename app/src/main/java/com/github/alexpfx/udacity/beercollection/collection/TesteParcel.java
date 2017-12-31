package com.github.alexpfx.udacity.beercollection.collection;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

/**
 * Created by alexandre on 30/12/17.
 */

public class TesteParcel implements Parcelable {

    public SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSparseBooleanArray(this.sparseBooleanArray);
    }

    public TesteParcel() {
    }

    protected TesteParcel(Parcel in) {
        this.sparseBooleanArray = in.readSparseBooleanArray();
    }

    public static final Parcelable.Creator<TesteParcel> CREATOR = new Parcelable.Creator<TesteParcel>() {
        @Override
        public TesteParcel createFromParcel(Parcel source) {
            return new TesteParcel(source);
        }

        @Override
        public TesteParcel[] newArray(int size) {
            return new TesteParcel[size];
        }
    };
}
