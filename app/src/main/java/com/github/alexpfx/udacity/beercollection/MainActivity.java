package com.github.alexpfx.udacity.beercollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.github.alexpfx.udacity.beercollection.databaselib.search.SearchPresenter;
import com.google.android.gms.auth.api.signin.internal.SignInHubActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends BaseActivity {

    public static final String KEY = BuildConfig.KEY_BREWERYDB;
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "MainActivity";


//    @Inject
    FirebaseAuth mFirebaseAuth;

    FirebaseAuth.AuthStateListener mAuthStateListener;

//    @Inject
    SearchPresenter searchPresenter;

//    @Inject
    FirebaseLoginManager firebaseLoginManager;


    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUsername = findViewById(R.id.tvUserName);




    }


    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(firebaseLoginManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(firebaseLoginManager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_signout:
                AuthUI.getInstance().signOut(this).addOnCompleteListener(task -> {
                    startActivity(new Intent(MainActivity.this, SignInHubActivity.class));
                    finish();

                });
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    protected void injectDependencies(BeerApp app) {
        app.getSearchSubComponent(this);
    }

}
