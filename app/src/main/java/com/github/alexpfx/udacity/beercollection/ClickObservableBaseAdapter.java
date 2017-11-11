package com.github.alexpfx.udacity.beercollection;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by alexandre on 04/11/17.
 */

public abstract class BaseAdapter <VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter <VH> {

    private PublishSubject<View> clickSubject = PublishSubject.create();

    //https://stackoverflow.com/questions/36497690/how-to-handle-item-clicks-for-a-recycler-view-using-rxjava
    public final Observable<View> getViewClickObservable (){
        return clickSubject.hide();
    }
    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate (LayoutInflater.from(parent.getContext()), parent);
        VH viewHolder = createViewHolder(view);
        RxView.clicks(view).takeUntil(RxView.detaches(parent)).map(a -> view).subscribe(clickSubject);
        return viewHolder;
    }

    protected abstract VH createViewHolder(View view);

    protected abstract View inflate(LayoutInflater inflater, ViewGroup parent);
}
