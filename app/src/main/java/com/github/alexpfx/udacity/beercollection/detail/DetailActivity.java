package com.github.alexpfx.udacity.beercollection.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.utils.CropMiddleFirstPixelTransformation;
import com.github.alexpfx.udacity.beercollection.utils.ToolbarUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements DetailFragment.Listener {

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Nullable
    @BindView(R.id.toolbar_image)
    ImageView imgToolbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.txt_toolbar_title)
    TextView toolbarTitle;


    public static Intent getStartIntent(Context context, String beerId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constants.KEY_BEER_ID, beerId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        ToolbarUtils.setupToolbar(this, toolbar, false, true, false, false);
    }

    @Override
    protected void injectDependencies(BeerApp app) {

    }

    @Override
    public void onTitleChanged(String title) {
        collapsingToolbarLayout.setTitle(title);
        toolbarTitle.setText(title);
    }


    private static final String TAG = "DetailActivity";
    @Override
    public void onImageChanged(String imgUrl) {
        if (imgToolbar == null){
            //tablet
            return;
        }

        int imageSize = Constants.DETAIL_BEER_LABEL_IMAGE_SIZE;

        Picasso.with(this).load(imgUrl)
                .placeholder(R.drawable.beerplaceholder)
                .error(R.drawable.ic_warning_white)
                .resize(imageSize, imageSize)
                .transform(new CropMiddleFirstPixelTransformation())
                .centerCrop()
                .into(imgToolbar);
    }
}


