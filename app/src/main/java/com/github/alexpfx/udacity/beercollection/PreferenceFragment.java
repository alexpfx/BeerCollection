package com.github.alexpfx.udacity.beercollection;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences
        .OnSharedPreferenceChangeListener {


    private Preference.OnPreferenceClickListener onResetClick = preference -> {
        Timber.d(preference.getKey());
        SwitchPreference switchPreference = (SwitchPreference) preference;

        startDialog(switchPreference);
        return false;
    };

    public PreferenceFragment() {
        // Required empty public constructor
    }

    private void startDialog(SwitchPreference switchPreference) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.message_confirm_reset_collection)).setTitle(getString(R.string
                .message_confirm_reset_data));

        builder.setPositiveButton(R.string.ok, (dialogInterface, i) -> switchPreference.setChecked(false));

        builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> switchPreference.setChecked(false));

        builder.create().show();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        setupEvents();


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
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        onResetClick = null;

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //

    }
}
