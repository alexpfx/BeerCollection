package com.github.alexpfx.udacity.beercollection.domain.model.database;

import com.github.alexpfx.udacity.beercollection.domain.model.User;

/**
 * Created by alexandre on 24/10/17.
 */

public class LocalType<T> {
    private User user;
    private long timestamp;
    private T data;


    public boolean isEmpty (){
        return data == null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
