package com.github.alexpfx.udacity.beercollection.preference;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.beer.collection.ClearCollectionPresenter;
import com.github.alexpfx.udacity.beercollection.beer.collection.ClearCollectionView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class PreferenceFragment extends PreferenceFragmentCompat implements ClearCollectionView {


    @Inject
    ClearCollectionPresenter presenter;

    private Preference.OnPreferenceClickListener onResetClick = preference -> {
        SwitchPreference switchPreference = (SwitchPreference) preference;

        startDialog(switchPreference);
        return false;
    };

    private Unbinder unbinder;


    public PreferenceFragment() {
    }


    private void startDialog(SwitchPreference switchPreference) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.message_confirm_reset_collection)).setTitle(getString(R.string
                .message_confirm_reset_data));

        builder.setPositiveButton(R.string.ok,
                (dialogInterface, i) -> {
                    switchPreference.setChecked(false);
                    clearCollection();
                });

        builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> switchPreference.setChecked(false));

        builder.create().show();
    }


    private void clearCollection() {
        presenter.clearCollection();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        setupEvents();
    }


    private void injectDependencies() {
        BeerApp app = (BeerApp) getActivity().getApplication();
        app.getMyCollectionSubComponent().inject(this);
        presenter.init(this);
    }


    private void setupEvents() {
        Preference preference = findPreference(getString(R.string.pref_key_reset_collection));
        preference.setOnPreferenceClickListener(onResetClick);
    }


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.main_preferences);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        onResetClick = null;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        presenter.unLoad();
    }


    @Override
    public void showClearDataSuccessful() {
        showSnackbarMessage(R.string.message_successful_clear_collection_data);
    }


    private void showSnackbarMessage(@StringRes int messageResId) {
        View coordinator = getActivity().findViewById(R.id.layout_coordinator);
        if (coordinator != null) {
            Snackbar.make(coordinator, getString(messageResId), Snackbar
                    .LENGTH_SHORT).show();
        }
    }


    @Override
    public void showClearDataError() {
        showSnackbarMessage(R.string.message_error_clear_collection_data);
    }
}
