package com.github.alexpfx.udacity.beercollection.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.Constants;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.ToolbarHelper;
import com.github.alexpfx.udacity.beercollection.search.CropMiddleFirstPixelTransformation;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements DetailFragment.Listener {
    private static final String TAG = "DetailActivity";

//    DetailPresenter detailPresenter;

//    private DetailViewHolder detailViewHolder;



    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;


    @BindView(R.id.toolbar_image)
    ImageView imgToolbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.txt_toolbar_title)
    TextView toolbarTitle;

    public static void startDetail(Context context, String beerId) {
        Log.d(TAG, "startDetail: ");
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constants.KEY_BEER_ID, beerId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        ToolbarHelper.setupToolbar(this, toolbar, false, true, false, false);
    }

    @Override
    protected void injectDependencies(BeerApp app) {

    }


    @Override
    protected void onPause() {
        super.onPause();
//        detailPresenter.onDestroy();
//        detailViewHolder.unbind();
    }

    @Override
    public void onTitleChanged(String title) {
        collapsingToolbarLayout.setTitle(title);
        toolbarTitle.setText(title);
    }

    @Override
    public void onImageChanged(String imgUrl) {
        Picasso.with(this).load(imgUrl)
                .resize(640, 640)
                .transform(new CropMiddleFirstPixelTransformation())
                .centerCrop()
                .into(imgToolbar);
    }
}


