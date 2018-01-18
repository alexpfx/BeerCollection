package com.github.alexpfx.udacity.beercollection.collection;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.DrinkBeerPresenter;
import com.github.alexpfx.udacity.beercollection.detail.DetailActivity;
import com.github.alexpfx.udacity.beercollection.preference.PreferenceActivity;
import com.github.alexpfx.udacity.beercollection.search.SearchActivity;
import com.github.alexpfx.udacity.beercollection.utils.ToolbarUtils;

import javax.inject.Inject;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectionActivity extends BaseActivity implements MyCollectionFragment.Listener {

    public static final String DETAIL_FRAGMENT = "detailFragment";
    private static final String TAG = "MyCollectionActivity";
    private static final String HISTORY_FRAGMENT_TAG = "historyFragment";
    @Inject
    DrinkBeerPresenter drinkBeerPresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.text_toolbar_title)
    TextView textToolbarTitle;


    @BindBool(R.bool.isMultiPane)
    boolean isMultiPane;
    @Nullable
    @BindView(R.id.guideline)
    Guideline guideline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);

        setupToolbar();

        getSupportFragmentManager().addOnBackStackChangedListener(() -> onBackStackChanged());

        if (savedInstanceState == null) {
            if (!isMultiPane) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_my_collection, new
                        MyCollectionFragment(), "collection").commit();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    private void setupToolbar() {
        ToolbarUtils.setupToolbar(this, toolbar, false, false, false, false);
        textToolbarTitle.setText(getString(R.string.activity_title_my_collection));

    }


    @Override
    protected void injectDependencies(BeerApp app) {
        app.getMyCollectionSubComponent().inject(this);
    }


    @Override
    public void navigateToDetail(String beerId) {
        if (!isMultiPane){
            Intent intent = DetailActivity.startIntent(getApplicationContext(), beerId);
            startActivity(intent);
        }else{

        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                if (supportFragmentManager.getBackStackEntryCount() > 0) {
                    supportFragmentManager.popBackStack();
                }
                return true;
            case R.id.action_settings:
                startSettingsActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startSettingsActivity() {
        Intent intent = new Intent(getApplicationContext(), PreferenceActivity.class);
        startActivity(intent);
    }


    private void onBackStackChanged() {
        updateVisibility();
    }

    /**
     * Shows/hide the fragment by moving the Guideline.
     */
    private void updateVisibility() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_pane2);
        if (fragment == null) {
            hideSecondPane();
        } else {
            showSecondPane();
        }

    }


    private void showSecondPane() {
        changeGuidePercent(0.7f);
    }

    private void hideSecondPane() {
        changeGuidePercent(1f);
    }


    private void changeGuidePercent(float percent) {
        if (guideline != null) {
            ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) guideline.getLayoutParams();
            lp.guidePercent = percent;
        }
    }


}
