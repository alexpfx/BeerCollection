package com.github.alexpfx.udacity.beercollection;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * Created by alexandre on 28/10/17.
 */

public class FirebaseLoginManager implements LoginManager, FirebaseAuth.AuthStateListener {


    private static final int RC_SIGN_IN = 10000;
    private static final String TAG = "FirebaseLoginManager";
    private final Activity activity;

    @Inject
    public FirebaseLoginManager(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            AuthUI.IdpConfig google = new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build();
            AuthUI.IdpConfig email = new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build();

            activity.startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(Arrays.asList(google, email))
                    .setIsSmartLockEnabled(false)
                    .build(), RC_SIGN_IN);
        }

    }
}
