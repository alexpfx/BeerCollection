package com.github.alexpfx.udacity.beercollection;

import android.content.Intent;
import android.os.Bundle;

import com.github.alexpfx.udacity.beercollection.util.FirebaseUtils;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity {

    public static final int REQUEST_CODE = 1;
    @Inject
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        processLogin();
    }

    private void processLogin() {
        if (firebaseAuth.getCurrentUser() != null) {
            startMainScreen();
        } else {
            FirebaseUtils.openAuthUi(this, REQUEST_CODE);
        }
    }

    private void startMainScreen() {
        startActivity(new Intent(this, SearchActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            startMainScreen();
        } else {
            finish();
        }
    }

    @Override
    protected void injectDependencies(BeerApp app) {
        app.getComponent().inject(this);
    }
}
