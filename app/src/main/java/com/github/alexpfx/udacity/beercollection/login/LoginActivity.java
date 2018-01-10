package com.github.alexpfx.udacity.beercollection.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.alexpfx.udacity.beercollection.BaseActivity;
import com.github.alexpfx.udacity.beercollection.BeerApp;
import com.github.alexpfx.udacity.beercollection.R;
import com.github.alexpfx.udacity.beercollection.collection.MyCollectionActivity;
import com.github.alexpfx.udacity.beercollection.databaselib.util.FirebaseUtils;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity {

    public static final int REQUEST_CODE = 1;

    @Inject
    FirebaseAuth firebaseAuth;

    public static void startLoginActivity (Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

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
        startActivity(new Intent(this, MyCollectionActivity.class));
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
