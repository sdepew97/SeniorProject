package com.example.apphomepages;

import android.app.Activity;

import com.example.apphomepages.Graph.Activities.GraphActivity;

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
public class GraphActivityTest
{
    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    @Rule
    public ActivityScenarioRule<GraphActivity> rule
            = new ActivityScenarioRule<>(GraphActivity.class);

    @Rule
    public IntentsTestRule<GraphActivity> intentsTestRule = new IntentsTestRule<>(GraphActivity.class);

    @Test
    public void testGraphCreated()
    {
        ActivityScenario<GraphActivity> scenario = ActivityScenario.launch(GraphActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
    }

    @Test
    public void testGraphStarted()
    {
        ActivityScenario<GraphActivity> scenario = ActivityScenario.launch(GraphActivity.class);
        scenario.moveToState(Lifecycle.State.STARTED);
    }

    @Test
    public void testGraphResumed()
    {
        ActivityScenario<GraphActivity> scenario = ActivityScenario.launch(GraphActivity.class);
        scenario.moveToState(Lifecycle.State.RESUMED);
    }

    @Test
    public void swipeThroughPages()
    {
        //Swipe to the end
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());

        //Swipe back to the beginning
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight());
    }

    //TODO: make sure all buttons can be clicked

    @Test
    public void testGraphDestroyed()
    {
        ActivityScenario<GraphActivity> scenario = ActivityScenario.launch(GraphActivity.class);
        scenario.moveToState(Lifecycle.State.DESTROYED);
    }

    @Test
    public void testGraphRecreated()
    {
        ActivityScenario<GraphActivity> scenario = ActivityScenario.launch(GraphActivity.class);
        scenario.recreate();
    }
}