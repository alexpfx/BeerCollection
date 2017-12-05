package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TooltipCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.AbstractBaseAdapter;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.databaselib.dagger.PerActivity;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.domain.model.collection.CollectionItem;
import com.github.alexpfx.udacity.beercollection.search.CropMiddleFirstPixelTransformation;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.DateFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by alexandre on 10/11/17.
 */
@PerActivity
public class CollectionAdapter extends AbstractBaseAdapter<CollectionAdapter.CollectionViewHolder,
        CollectionItem> {

    private final PublishSubject<View> clickDetailSubject = PublishSubject.create();
    private final PublishSubject<View> clickAddBeerSubject = PublishSubject.create();
    private final PublishSubject<View> clickHistorySubject = PublishSubject.create();


    @Inject
    public CollectionAdapter() {
    }

    public Observable<View> getDetailClickSubject() {
        return clickDetailSubject.hide();
    }

    @Override
    protected CollectionViewHolder createViewHolder(View view) {
        return new CollectionViewHolder(view);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_collection, parent, false);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public PublishSubject<View> getClickAddBeerSubject() {
        return clickAddBeerSubject;
    }

    public PublishSubject<View> getClickHistorySubject() {
        return clickHistorySubject;
    }


    public class CollectionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_beer_label)
        ImageView imageBeerLabel;
        @BindView(R.id.text_beer_name)
        TextView textBeerName;
        @BindView(R.id.text_last_drink_date)
        TextView textLastDrinkDate;
        @BindView(R.id.text_quantity)
        TextView textQuantity;

        @BindView(R.id.view_scrim)
        View viewScrim;

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageBeerLabel.setImageBitmap(bitmap);
                Palette.from(bitmap).generate(palette -> {
//                    Palette.Swatch vibrantSwatch = palette.getDarkVibrantSwatch();
                    Palette.Swatch vibrantSwatch = palette.getDominantSwatch();

//                    Palette.Swatch vibrantSwatch = palette.getDarkMutedSwatch();
//                    Palette.Swatch vibrantSwatch = palette.getLightMutedSwatch();


                    if (vibrantSwatch != null) {
                        int rgbScrim = ColorUtils.setAlphaComponent(vibrantSwatch.getRgb(), (int) (255 * 0.2f));
                        int rgbTextBackground = ColorUtils.setAlphaComponent(vibrantSwatch.getRgb(), (int) (255 *
                                0.75f));

                        int textRgb = vibrantSwatch.getBodyTextColor();

                        viewScrim.setBackgroundColor(rgbScrim);

                        textBeerName.setBackgroundColor(rgbTextBackground);
                        textBeerName.setTextColor(textRgb);

                        textLastDrinkDate.setBackgroundColor(rgbTextBackground);
                        textLastDrinkDate.setTextColor(textRgb);


                        textQuantity.setBackgroundColor(rgbTextBackground);
                        textQuantity.setTextColor(textRgb);

                    }
                });

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        private Context context;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }
/*
        View view = inflate(LayoutInflater.from(parent.getContext()), parent);
        VH viewHolder = createViewHolder(view);
        RxView.clicks(view).takeUntil(RxView.detaches(parent)).map(a -> view).subscribe(clickSubject);
        return viewHolder;*/

        public void bind(CollectionItem collectionItemVO) {
            Beer beer = collectionItemVO.getBeer();

            setupLabelView(beer);

            setupBeerNameView(collectionItemVO, beer);

            setupQuantityView(collectionItemVO);

            setupLastDrinkDateView(collectionItemVO);

        }

        private void setupLabelView(Beer beer) {
            Picasso.with(context)
                    .load(beer.getLabelLarge())
                    .resize(512, 512)
                    .transform(new CropMiddleFirstPixelTransformation())
                    .placeholder(R.drawable.beerplaceholder)
                    .centerCrop()
                    .into(target);

            setBeerIdViewTag(imageBeerLabel, beer.getId());

            RxView.clicks(imageBeerLabel).takeUntil(RxView.detaches((View) imageBeerLabel.getParent())).map(a ->
                    imageBeerLabel).subscribe(clickDetailSubject);

        }


        private void setupBeerNameView(CollectionItem collectionItemVO, Beer beer) {
            setBeerIdViewTag(textBeerName, beer.getId());
            textBeerName.setText(beer.getName());
            setTooltipText(textBeerName, R.string.tooltip_beer_name);

            RxView.clicks(textBeerName).takeUntil(RxView.detaches((View) textBeerName.getParent())).map(a ->
                    textBeerName).subscribe(clickDetailSubject);
        }

        private void setBeerIdViewTag(View view, String id) {
            view.setTag(id);
        }

        private void setupLastDrinkDateView(CollectionItem collectionItem) {
            DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.SHORT);
            CharSequence dateFormated = dateInstance.format(collectionItem.getLastDate());
            textLastDrinkDate.setText(dateFormated);
            setTooltipText(textLastDrinkDate, R.string.tooltip_last_beer);

            setBeerIdViewTag(textLastDrinkDate, collectionItem.getBeer().getId());

            RxView.clicks(textLastDrinkDate).takeUntil(RxView.detaches((View) textLastDrinkDate.getParent())).map(a ->
                    textLastDrinkDate).subscribe(clickHistorySubject);
        }

        private void setupQuantityView(CollectionItem collectionItem) {
            textQuantity.setText(String.valueOf(collectionItem.countBeers()));
            setTooltipText(textQuantity, R.string.tooltip_quantity);

            setBeerIdViewTag(textQuantity, collectionItem.getBeer().getId());

//            RxView.clicks(textQuantity).takeUntil(RxView.detaches((View) textQuantity.getParent())).map(a ->
//                    textQuantity).subscribe(clickAddBeerSubject);

            RxView.clicks(textQuantity).map(a -> textQuantity).subscribe(clickAddBeerSubject);


        }

        private void setTooltipText(View view, int resId) {
            TooltipCompat.setTooltipText(view, getString(resId));
        }

        private String getString(@StringRes int id) {
            return context.getString(id);
        }


    }


}




