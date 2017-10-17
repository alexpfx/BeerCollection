package com.github.alexpfx.udacity.beercollection;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.github.alexpfx.udacity.beercollection.domain.client.BreweryDBService;
import com.github.alexpfx.udacity.beercollection.domain.model.network.SearchBeerResponse;
import com.google.android.gms.auth.api.signin.internal.SignInHubActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MainActivity extends BaseActivity {

    public static final String KEY = "add it to an external project constant";
    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "MainActivity";
    @Inject
    BreweryDBService service;
    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);

        tvUsername = findViewById(R.id.tvUserName);




        Flowable<SearchBeerResponse> stella = service.searchBeers(KEY, "stella");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();


        DatabaseReference reference = mFirebaseDatabase.getReference("usuario");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot == null) {
                    return;
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        reference.setValue("hello, world");


        Log.d(TAG, "onCreate: q: " + stella.count());

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();

            if (currentUser != null) {


                onSignInIntialize (currentUser);



            } else {

                onSignOutCleanUp ();

                AuthUI.IdpConfig google = new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build();
                AuthUI.IdpConfig email = new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build();

                startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(google, email))
                        .setIsSmartLockEnabled(false)

                        .build(), RC_SIGN_IN)

                ;

            }

        };

    }

    private void onSignOutCleanUp() {

    }

    private void onSignInIntialize(FirebaseUser displayName) {
        tvUsername.setText(TextUtils.concat(
                displayName.getDisplayName(),
                ", ",
                displayName.getEmail(),
                ", ",
                displayName.getPhoneNumber(),
                ", ",
                displayName.getProviderId(),
                ", ",
                displayName.getProviderData().toString(),
                ", ",
                displayName.getUid()

                )

        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                Toast.makeText(this, "You are sign in!", Toast.LENGTH_SHORT).show();
            }else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Sign in canceled!", Toast.LENGTH_SHORT).show();
            }


        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);

    }


}
