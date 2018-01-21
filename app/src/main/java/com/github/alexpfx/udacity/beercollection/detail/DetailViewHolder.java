package com.github.alexpfx.udacity.beercollection.detail;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;
import com.github.alexpfx.udacity.beercollection.utils.CropMiddleFirstPixelTransformation;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.PublishSubject;

import static com.github.alexpfx.udacity.beercollection.utils.TextViewUtils.valueOrDash;

/**
 * Created by alexandre on 07/11/17.
 */


public class DetailViewHolder {

    @BindView(R.id.txt_short_style)
    TextView txtShortStyle;

    @BindView(R.id.txt_style_name)
    TextView txtStyle;

    @BindView(R.id.txt_style_category_name)
    TextView txtStyleCategory;

    @BindView(R.id.txt_beer_name)
    TextView txtName;

    @BindView(R.id.txt_beer_description)
    TextView txtDescription;

    @BindView(R.id.txt_ibu)
    TextView txtIbu;

    @BindView(R.id.txt_abv)
    TextView txtAbv;

    @BindView(R.id.txt_srm)
    TextView txtSrm;

//    @BindView(R.id.txt_organic)
//    TextView txtOrganic;

    @BindView(R.id.img_srm_color)
    ImageView imgSrmColor;

    @BindView(R.id.txt_serving_temperature)
    TextView txtServingTemperature;

    @BindView(R.id.txt_glass)
    TextView txtGlass;

    @BindView(R.id.txt_brewery_name)
    TextView txtBrewery;

    @BindView(R.id.txt_brewery_website)
    TextView txtBreweryWebsite;

    /* Present only in the tablet  version */
    @Nullable
    @BindView(R.id.img_beer_label)
    ImageView imgBeerLabel;

    @BindBool(R.bool.isMultiPane)
    boolean isMultiPane;

    private WeakReference<Context> contextWeakReference;


    private Unbinder unbinder;

    private PublishSubject<View> beerClickSubject = PublishSubject.create();


    public DetailViewHolder(View view) {
        unbinder = ButterKnife.bind(this, view);
        contextWeakReference = new WeakReference<Context>(view.getContext());
    }


    public void setBeer(Beer beer) {

        txtName.setText(beer.getName());
        txtDescription.setText(valueOrDash(beer.getDescription()));

        txtShortStyle.setText(valueOrDash(beer.getShortStyle()));
        txtStyleCategory.setText(valueOrDash(beer.getStyleCategory()));
        txtStyle.setText(valueOrDash(beer.getStyle()));

        txtAbv.setText(valueOrDash(beer.getAbv()));
        txtIbu.setText(valueOrDash(beer.getIbu()));

        txtSrm.setText(valueOrDash(beer.getSrm()));
//        txtOrganic.setText(valueOrDash(beer.getOrganic()));

        imgSrmColor.setBackgroundColor(getColor(beer.getSrmHexColor(), Color.TRANSPARENT));
        txtServingTemperature.setText(valueOrDash(beer.getServingTemperature()));

        txtGlass.setText(valueOrDash(beer.getGlass()));

        txtBrewery.setText(valueOrDash(beer.getBreweryName()));
        txtBreweryWebsite.setText(valueOrDash(beer.getBreweryWebsite()));

        if (isMultiPane) {
            Picasso.with(contextWeakReference.get()).load(beer.getLabelLarge())
                    .placeholder(R.drawable.beerplaceholder)
                    .error(R.drawable.ic_warning_white)
                    .resize(640, 640)
                    .transform(new CropMiddleFirstPixelTransformation())
                    .centerCrop()
                    .into(imgBeerLabel);

        }


    }

    private int getColor(String srmHexColor, int defaultColor) {
        try {
            return srmHexColor == null ? defaultColor : Color.parseColor("#" + srmHexColor);
        } catch (IllegalArgumentException e) {
            return defaultColor;
        }
    }

    public void unbind() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
