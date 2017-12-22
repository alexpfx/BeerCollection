package com.github.alexpfx.udacity.beercollection.detail;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.domain.model.beer.Beer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.PublishSubject;

import static com.github.alexpfx.udacity.beercollection.TextViewUtils.valueOrDash;

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


    /*@BindView(R.id.img_label)
    ImageView imgLabel;


    @BindView(R.id.txt_brewery)
    TextView txtBrewery;




    @BindView(R.id.btn_drink)
    ImageButton btnDrink;*/

    private Unbinder unbinder;
    private Context context;

    private PublishSubject<View> beerClickSubject = PublishSubject.create();


    public DetailViewHolder(View view) {
        context = view.getContext();
        unbinder = ButterKnife.bind(this, view);

        /*RxView.clicks(btnDrink).map(a -> btnDrink).subscribe
                (beerClickSubject);*/
    }

    /*public Observable<View> getBeerClickObservable() {
        return beerClickSubject.hide();
    }*/

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



/*
//        txtBrewery.setText();





        Picasso.with(context).load(beer.getLabelLarge()).resize(200,200).placeholder(R.drawable.beerplaceholder)
        .centerCrop().into(imgLabel);*/

    }

    private int getColor(String srmHexColor, int defaultColor) {
        try {
            return srmHexColor == null ? defaultColor : Color.parseColor("#"+srmHexColor);
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
