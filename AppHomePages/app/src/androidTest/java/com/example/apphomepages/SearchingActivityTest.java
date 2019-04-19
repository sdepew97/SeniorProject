package com.example.apphomepages;

import android.app.Activity;

import com.example.apphomepages.SearchAndSort.Activities.SearchingActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

//I Used https://github.com/googlesamples/android-testing and the Android documentation when writing these tests

/**
 * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
 * {@link ViewActions#click} and {@link ViewActions#typeText}.
 * <p>
 * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchingActivityTest
{
    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    @Rule
    public ActivityScenarioRule<SearchingActivity> rule
            = new ActivityScenarioRule<>(SearchingActivity.class);

    @Rule
    public IntentsTestRule<SearchingActivity> intentsTestRule = new IntentsTestRule<>(SearchingActivity.class);

    @Test
    public void testSearchingCreated()
    {
        ActivityScenario<SearchingActivity> scenario = ActivityScenario.launch(SearchingActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
    }

    @Test
    public void testSearchingStarted()
    {
        ActivityScenario<SearchingActivity> scenario = ActivityScenario.launch(SearchingActivity.class);
        scenario.moveToState(Lifecycle.State.STARTED);
    }

    @Test
    public void testSearchingResumed()
    {
        ActivityScenario<SearchingActivity> scenario = ActivityScenario.launch(SearchingActivity.class);
        scenario.moveToState(Lifecycle.State.RESUMED);
    }

    @Test
    public void swipeThroughPages()
    {
        //Swipe to the end
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());

        //Swipe back to the beginning
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight());
    }

    @Test
    public void checkInstructionsLinear() throws InterruptedException
    {
        //Check Linear Search instructions
        onView(withId(R.id.instructionsButtonLinear)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withId(R.id.scrollLinearInstructions)).perform(ViewActions.swipeUp());
        onView(withId(R.id.close_instructions_linear)).perform(ViewActions.click());
    }

    @Test
    public void checkInstructionsBinary() throws InterruptedException
    {
        //Check Binary Search instructions
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.instructionsButtonBinary)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withId(R.id.scrollBinaryInstructions)).perform(ViewActions.swipeUp());
        onView(withId(R.id.close_instructions_binary)).perform(ViewActions.click());
    }

    @Test
    public void checkGenerateLinear()
    {
        onView(withId(R.id.generateButtonLinear)).perform(ViewActions.click());
    }

    @Test
    public void checkGenerateBinary() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.generateButtonBinary)).perform(ViewActions.click());
    }

    @Test
    public void checkSpinnerLinear()
    {
        onView(withId(R.id.spinnerLinear)).perform(ViewActions.click());
    }

    @Test
    public void checkSpinnerBinary() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.spinnerBinary)).perform(ViewActions.click());
    }

    @Test
    public void checkStartLinear()
    {
        onView(withId(R.id.startButtonLinear)).perform(ViewActions.click());
    }

    @Test
    public void checkStartBinary() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.startButtonBinary)).perform(ViewActions.click());
    }

    @Test
    public void checkStopLinear()
    {
        onView(withId(R.id.stopButtonLinear)).perform(ViewActions.click());
    }

    @Test
    public void checkStopBinary() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.stopButtonBinary)).perform(ViewActions.click());
    }

    @Test
    public void checkRewindLinear()
    {
        onView(withId(R.id.rewindButtonLinear)).perform(ViewActions.click());
    }

    @Test
    public void checkRewindBinary() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.rewindButtonBinary)).perform(ViewActions.click());
    }

    @Test
    public void checkProofLinear()
    {
        onView(withId(R.id.scrollProofLinear)).perform(ViewActions.swipeUp());
        onView(withId(R.id.proofButtonLinear)).perform(ViewActions.click());
    }

    @Test
    public void checkProofBinary() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.scrollProofBinary)).perform(ViewActions.swipeUp());
        onView(withId(R.id.proofButtonBinary)).perform(ViewActions.click());
    }

    @Test
    public void testSearchingDestroyed()
    {
        ActivityScenario<SearchingActivity> scenario = ActivityScenario.launch(SearchingActivity.class);
        scenario.moveToState(Lifecycle.State.DESTROYED);
    }

    @Test
    public void testSearchingRecreated()
    {
        ActivityScenario<SearchingActivity> scenario = ActivityScenario.launch(SearchingActivity.class);
        scenario.recreate();
    }
}