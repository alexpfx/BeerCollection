package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by alexandre on 02/01/18.
 */

public class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
    private static final String TAG = "RecyclerViewTouchListen";
    private Context context;

    private GestureDetectorCompat gestureDetectorCompat;
    private PublishSubject<View> longPress;
    private PublishSubject<View> click;

    public RecyclerViewTouchListener(Context context,
                                     PublishSubject<View> click,
                                     PublishSubject<View> longPress,
                                     RecyclerView recyclerView) {
        this.context = context;
        this.click = click;
        gestureDetectorCompat =
                new GestureDetectorCompat
                        (context, new MSimpleGestureListener(longPress, recyclerView));
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View view = rv.findChildViewUnder(e.getX(), e.getY());
        boolean longPressEvent = gestureDetectorCompat.onTouchEvent(e);

        if (view != null && longPressEvent) {
            click.onNext(view);
            Log.d(TAG, "onInterceptTouchEvent: ");
            return true;
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private static class MSimpleGestureListener extends GestureDetector.SimpleOnGestureListener {
        private PublishSubject<View> longClick;
        private RecyclerView recyclerView;

        public MSimpleGestureListener(PublishSubject<View> longClick, RecyclerView recyclerView) {
            this.longClick = longClick;
            this.recyclerView = recyclerView;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                longClick.onNext(view);
            }
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }
    }
}
