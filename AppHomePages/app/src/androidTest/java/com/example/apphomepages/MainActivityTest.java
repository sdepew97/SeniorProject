package com.example.apphomepages;

import android.app.Activity;

import com.example.apphomepages.DynamicProgramming.Activities.DynamicProgrammingActivity;
import com.example.apphomepages.Graph.Activities.GraphActivity;
import com.example.apphomepages.Greedy.Activities.GreedyActivity;
import com.example.apphomepages.Main.Activities.MainActivity;
import com.example.apphomepages.SearchAndSort.Activities.SearchingActivity;
import com.example.apphomepages.SearchAndSort.Activities.SortingActivity;

import org.junit.Rule;
import org.junit.Test;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

//I Used https://github.com/googlesamples/android-testing and the Android documentation when writing these tests

/**
 * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
 * {@link ViewActions#click} and {@link ViewActions#typeText}.
 * <p>
 * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
 */
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
    public void clickSearching()
    {
        onView(withId(R.id.searchingButton)).perform(ViewActions.click());
        intended(hasComponent(SearchingActivity.class.getName()));
    }

    //Used https://stackoverflow.com/questions/25998659/espresso-how-can-i-check-if-an-activity-is-launched-after-performing-a-certain
    @Test
    public void clickSorting()
    {
        onView(withId(R.id.sortingButton)).perform(ViewActions.click());
        intended(hasComponent(SortingActivity.class.getName()));
    }

    @Test
    public void clickGraph() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        //TODO: figure out better solution than waiting here!
        Thread.sleep(500);
        onView(withId(R.id.graphAlgorithms)).perform(ViewActions.click());
        intended(hasComponent(GraphActivity.class.getName()));
    }

    @Test
    public void clickGreedy() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        //TODO: figure out better solution than waiting here!
        Thread.sleep(500);
        onView(withId(R.id.greedyAlgorithms)).perform(ViewActions.click());
        intended(hasComponent(GreedyActivity.class.getName()));
    }

    @Test
    public void clickDP() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        //TODO: figure out better solution than waiting here!
        Thread.sleep(500);
        onView(withId(R.id.dynamicProgrammingAlgorithms)).perform(ViewActions.click());
        intended(hasComponent(DynamicProgrammingActivity.class.getName()));
    }

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