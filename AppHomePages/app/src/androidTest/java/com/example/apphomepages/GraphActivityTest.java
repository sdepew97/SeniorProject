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

    @Test
    public void checkInstructionsDFS() throws InterruptedException
    {
        onView(withId(R.id.instructionsButtonDFS)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withId(R.id.scrollDFSInstructions)).perform(ViewActions.swipeUp());
        onView(withId(R.id.close_instructions_dfs)).perform(ViewActions.click());
    }

    @Test
    public void checkInstructionsBFS() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.instructionsButtonBFS)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withId(R.id.scrollBFSInstructions)).perform(ViewActions.swipeUp());
        onView(withId(R.id.close_instructions_bfs)).perform(ViewActions.click());
    }

    @Test
    public void checkInstructionsTopological() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.instructionsButtonTopological)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withId(R.id.scrollTopologicalInstructions)).perform(ViewActions.swipeUp());
        onView(withId(R.id.close_instructions_topological)).perform(ViewActions.click());
    }

    @Test
    public void checkGenerateDFS() throws InterruptedException
    {
        onView(withId(R.id.generateButtonDFS)).perform(ViewActions.click());
    }

    @Test
    public void checkGenerateBFS() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.generateButtonBFS)).perform(ViewActions.click());
    }

    @Test
    public void checkSpinnerDFS()
    {
        onView(withId(R.id.spinnerSelectDFS)).perform(ViewActions.click());
    }

    @Test
    public void checkSpinnerBFS() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.spinnerSelectBFS)).perform(ViewActions.click());
    }

    @Test
    public void checkStartDFS()
    {
        onView(withId(R.id.startButtonDFS)).perform(ViewActions.click());
    }

    @Test
    public void checkStartBFS() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.startButtonBFS)).perform(ViewActions.click());
    }

    @Test
    public void checkStartTopological() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.startButtonTopological)).perform(ViewActions.click());
    }

    @Test
    public void checkStopDFS()
    {
        onView(withId(R.id.stopButtonDFS)).perform(ViewActions.click());
    }

    @Test
    public void checkStopBFS() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.stopButtonBFS)).perform(ViewActions.click());
    }

    @Test
    public void checkStopTopological() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.stopButtonTopological)).perform(ViewActions.click());
    }

    @Test
    public void checkRewindDFS()
    {
        onView(withId(R.id.rewindButtonDFS)).perform(ViewActions.click());
    }

    @Test
    public void checkRewindBFS() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.rewindButtonBFS)).perform(ViewActions.click());
    }

    @Test
    public void checkRewindTopological() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.rewindButtonTopological)).perform(ViewActions.click());
    }

    @Test
    public void checkProofDFS() throws InterruptedException
    {
        onView(withId(R.id.scrollProofDFS)).perform(ViewActions.swipeUp());
        onView(withId(R.id.proofButtonDFS)).perform(ViewActions.click());
    }

    @Test
    public void checkProofBFS() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.scrollProofBFS)).perform(ViewActions.swipeUp());
        onView(withId(R.id.proofButtonBFS)).perform(ViewActions.click());
    }

    @Test
    public void checkProofTopological() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.scrollProofTopological)).perform(ViewActions.swipeUp());
        onView(withId(R.id.proofButtonTopological)).perform(ViewActions.click());
    }

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