package com.github.alexpfx.udacity.beercollection.domain.model.local;

/**
 * Created by alexandre on 24/10/17.
 */

public class LocalType<T> {
    private long timestamp;
    private T data;


    public boolean isEmpty() {
        return data == null;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
