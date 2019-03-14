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

    //TODO: make sure all buttons can be clicked

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