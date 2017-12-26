package com.github.alexpfx.udacity.beercollection.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.AbstractBaseAdapter;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by alexandre on 31/10/17.
 */
@PerActivity
public class SearchAdapter extends AbstractBaseAdapter<SearchAdapter.SeachViewHolder, Beer> {
    private static final String TAG = "SearchAdapter";
    private List<Beer> items = new ArrayList<>();
    private PublishSubject<View> clickDetailViewObservable = PublishSubject.create();
    private PublishSubject<View> clickDownloadViewObservable = PublishSubject.create();

    @Inject
    public SearchAdapter() {

    }

    public Observable<View> getClickDownloadViewObservable() {
        return clickDownloadViewObservable.hide();
    }

    public Observable<View> getClickDetailViewObservable() {
        return clickDetailViewObservable.hide();
    }

    @Override
    protected SeachViewHolder createViewHolder(View view) {
        return new SeachViewHolder(view);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_search_beer, parent, false);

        return view;
    }

    @Override
    public void onBindViewHolder(SeachViewHolder holder, int position) {
        Beer beer = items.get(position);

        holder.bind(beer);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void setItems(List<Beer> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class SeachViewHolder extends RecyclerView.ViewHolder {
        private final View itemView;

        @BindView(R.id.txt_beer_name)
        TextView txtBeerName;

        @BindView(R.id.txt_beer_style)
        TextView txtBeerStyle;

        @BindView(R.id.img_beer_label)
        ImageView imgBeerLabel;

        @BindView((R.id.btn_download))
        ImageButton btnDownload;


        private WeakReference<Context> context;

        public SeachViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            RxView.clicks(itemView).map(a -> itemView).subscribe(clickDetailViewObservable);
            RxView.clicks(btnDownload).map(a -> btnDownload).subscribe(clickDownloadViewObservable);
            context = new WeakReference<>(itemView.getContext());
            this.itemView = itemView;
        }


        public void bind(Beer beer) {
            itemView.setTag(beer.getId());
            btnDownload.setTag(beer.getId());


            txtBeerName.setText(beer.getName());
            txtBeerStyle.setText(beer.getStyle());

            Picasso.with(context.get()).load(beer.getLabelIcon())
                    .resize(320, 320)
                    .transform(new CropMiddleFirstPixelTransformation())
                    .transform(new CropCircleTransformation())
                    .centerCrop()
                    .into(imgBeerLabel);

        }



    }

}
