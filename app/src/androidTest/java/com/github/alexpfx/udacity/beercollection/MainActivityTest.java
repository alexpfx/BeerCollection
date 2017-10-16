package com.github.alexpfx.udacity.beercollection;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.alexpfx.udacity.beercollection.domain.client.BreweryDBService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by alexandre on 14/10/17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class, true, false);


    @Inject
    BreweryDBService service;


    @Before
    public void setUp (){
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        BeerApp app = (BeerApp) instrumentation.getTargetContext().getApplicationContext();
    }


    @Test
    public void testLaunchActivity (){
        mainActivityActivityTestRule.launchActivity(new Intent());
        SystemClock.sleep(5000);


    }







}