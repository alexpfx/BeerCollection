package com.github.alexpfx.udacity.beercollection.collection.adapter;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Target usado para realizar transformações na view de item collection utilizando a biblioteca Palette.
 */
class LabelCollectionImageViewTarget implements Target {

    public static final int ALPHA = 100;

    private CollectionViewHolder collectionViewHolder;


    public LabelCollectionImageViewTarget(CollectionViewHolder collectionViewHolder) {
        this.collectionViewHolder = collectionViewHolder;
    }


    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        collectionViewHolder.imageBeerLabel.setImageBitmap(bitmap);

        Palette.from(bitmap).generate(palette -> {
            Palette.Swatch swatch = palette.getDominantSwatch();
            Palette.Swatch vibrantSwatch = palette.getDarkVibrantSwatch();

            int textColor = swatch.getTitleTextColor();

            int rgb = swatch.getRgb();

            collectionViewHolder.layout.setBackgroundColor(rgb);

            collectionViewHolder.textBeerName.setTextColor(textColor);
            collectionViewHolder.textQuantity.setTextColor(rgb);

            int alphaTitleTextColor = alpha(textColor);
            collectionViewHolder.textQuantity.setBackgroundColor(alphaTitleTextColor);

            collectionViewHolder.textLastDrinkDate.setTextColor(rgb);
            collectionViewHolder.textLastDrinkDate.setBackgroundColor(alphaTitleTextColor);

            PorterDuff.Mode mode = PorterDuff.Mode.MULTIPLY;
            if (vibrantSwatch != null) {
                collectionViewHolder.btnDrink.getDrawable().setColorFilter(vibrantSwatch.getRgb(), mode);
            } else {
                collectionViewHolder.btnDrink.getDrawable().setColorFilter(rgb, mode);
            }
        });
    }


    int alpha(int color) {
        return ColorUtils.setAlphaComponent(color, ALPHA);
    }


    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }


    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}
