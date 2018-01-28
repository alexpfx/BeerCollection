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
import android.view.inputmethod.InputMethodManager;

import com.firebase.ui.auth.AuthUI;
import com.github.alexpfx.udacity.beercollection.login.LoginActivity;
import com.mikepenz.iconics.context.IconicsContextWrapper;

import java.lang.reflect.Field;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies((BeerApp) getApplicationContext());
    }

    protected abstract void injectDependencies(BeerApp app);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fixInputMethod(this);
    }


    /**
     * Fix memory leak coming from Android Source code.
     * <p>
     * https://issuetracker.google.com/issues/37043700#comment17
     * <p>
     * fixInputMethod
     *
     * @param context Context
     * @author androidmalin
     */
    public static void fixInputMethod(Context context) {
        if (context == null) return;
        InputMethodManager inputMethodManager = null;
        try {
            inputMethodManager = (InputMethodManager) context.getApplicationContext().getSystemService(Context
                    .INPUT_METHOD_SERVICE);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (inputMethodManager == null) return;
        String[] strArr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        for (int i = 0; i < 3; i++) {
            try {
                Field declaredField = inputMethodManager.getClass().getDeclaredField(strArr[i]);
                if (declaredField == null) continue;
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                Object obj = declaredField.get(inputMethodManager);
                if (obj == null || !(obj instanceof View)) continue;
                View view = (View) obj;
                if (view.getContext() == context) {
                    declaredField.set(inputMethodManager, null);
                } else {
                    return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
