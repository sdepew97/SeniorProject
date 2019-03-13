package com.example.apphomepages;

import android.app.Activity;

import com.example.apphomepages.Main.Activities.MainActivity;

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
import static androidx.test.espresso.action.ViewActions.click;
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
public class MainActivityTest
{
    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    @Rule
    public ActivityScenarioRule<MainActivity> rule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testMainCreated()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
    }

    @Test
    public void testMainStarted()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.moveToState(Lifecycle.State.STARTED);
    }

    @Test
    public void testMainResumed()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.moveToState(Lifecycle.State.RESUMED);
    }

    //Not a supported state
    /*
    @Test
    public void testMainInitialized()
    {
        rule.getScenario().moveToState(Lifecycle.State.INITIALIZED);
    }
    */

    @Test
    public void swipeThroughPages()
    {
        //Swipe to the end
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());

        //Swipe back to the beginning
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight());
    }

    @Test
    public void selectSearching()
    {
        onView(withId(R.id.searchingButton)).perform(click());
    }

    @Test
    public void selectSorting()
    {
        onView(withId(R.id.sortingButton)).perform(click());
    }

    /*
    @Test
    public void selectGraph()
    {
        //onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.graphAlgorithms))
                .perform(scrollTo(), click());
        //onView(withId(R.id.graphAlgorithms)).perform(click());
    }

    @Test
    public void selectGreedy()
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.greedyAlgorithms)).perform(click());
    }

    @Test
    public void selectDynamicProgramming()
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.dynamicProgrammingAlgorithms)).perform(click());
    }
    */

    @Test
    public void testMainDestroyed()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.moveToState(Lifecycle.State.DESTROYED);
    }

    @Test
    public void testMainRecreated()
    {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.recreate();
    }
}