package com.github.alexpfx.udacity.beercollection.databaselib.util;

import android.app.Activity;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

public class FirebaseUtils {

    public static void openAuthUi (Activity activity, int requestCode){

        AuthUI.IdpConfig googleProvider = new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build();
        AuthUI.IdpConfig emailProvider = new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build();


        activity
                .startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(Arrays.asList(googleProvider, emailProvider))
                                .setIsSmartLockEnabled(false)
                                .build(), requestCode);
    }

}
