package com.github.alexpfx.udacity.beercollection;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.mikepenz.iconics.context.IconicsContextWrapper;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_signout:
                signOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener(
                        task -> Snackbar.make(getBaseContentView(), R.string.message_successful_signout,
                                Snackbar.LENGTH_SHORT).show());
        LoginActivity.startLoginActivity(getApplicationContext());

    }

    protected View getBaseContentView() {
        return findViewById(android.R.id.content);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies((BeerApp) getApplicationContext());
    }

    protected abstract void injectDependencies(BeerApp app);

}
