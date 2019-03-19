package com.example.apphomepages;

import android.app.Activity;

import com.example.apphomepages.SearchAndSort.Activities.SortingActivity;

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
public class SortingActivityTest
{
    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    @Rule
    public ActivityScenarioRule<SortingActivity> rule
            = new ActivityScenarioRule<>(SortingActivity.class);

    @Rule
    public IntentsTestRule<SortingActivity> intentsTestRule = new IntentsTestRule<>(SortingActivity.class);

    @Test
    public void testSortingCreated()
    {
        ActivityScenario<SortingActivity> scenario = ActivityScenario.launch(SortingActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
    }

    @Test
    public void testSortingStarted()
    {
        ActivityScenario<SortingActivity> scenario = ActivityScenario.launch(SortingActivity.class);
        scenario.moveToState(Lifecycle.State.STARTED);
    }

    @Test
    public void testSortingResumed()
    {
        ActivityScenario<SortingActivity> scenario = ActivityScenario.launch(SortingActivity.class);
        scenario.moveToState(Lifecycle.State.RESUMED);
    }

    @Test
    public void swipeThroughPages()
    {
        //Swipe to the end
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());

        //Swipe back to the beginning
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeRight());
    }

    @Test
    public void checkInstructionsBubble() throws InterruptedException
    {
        onView(withId(R.id.instructionsButtonBubble)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withId(R.id.scrollBubbleInstructions)).perform(ViewActions.swipeUp());
        onView(withId(R.id.close_instructions_bubble)).perform(ViewActions.click());
    }

    @Test
    public void checkInstructionsInsertion() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.instructionsButtonInsertion)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withId(R.id.scrollInsertionInstructions)).perform(ViewActions.swipeUp());
        onView(withId(R.id.close_instructions_insertion)).perform(ViewActions.click());
    }

    @Test
    public void checkInstructionsSelection() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.instructionsButtonSelection)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withId(R.id.scrollSelectionInstructions)).perform(ViewActions.swipeUp());
        onView(withId(R.id.close_instructions_selection)).perform(ViewActions.click());
    }

    @Test
    public void checkInstructionsMerge() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.instructionsButtonMerge)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withId(R.id.scrollMergeInstructions)).perform(ViewActions.swipeUp());
        onView(withId(R.id.close_instructions_merge)).perform(ViewActions.click());
    }

    @Test
    public void checkInstructionsQuick() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.instructionsButtonQuick)).perform(ViewActions.click());
        Thread.sleep(500);
        onView(withId(R.id.scrollQuickInstructions)).perform(ViewActions.swipeUp());
        onView(withId(R.id.close_instructions_quick)).perform(ViewActions.click());
    }

    @Test
    public void checkGenerateBubble()
    {
        onView(withId(R.id.generateButtonBubble)).perform(ViewActions.click());
    }

    @Test
    public void checkGenerateInsertion() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.generateButtonInsertion)).perform(ViewActions.click());
    }

    @Test
    public void checkGenerateSelection() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.generateButtonSelection)).perform(ViewActions.click());
    }

    @Test
    public void checkGenerateMerge() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.generateButtonMerge)).perform(ViewActions.click());
    }

    @Test
    public void checkGenerateQuick() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.generateButtonQuick)).perform(ViewActions.click());
    }

    //Merge sort is the only screen with a spinner currently
    @Test
    public void checkSpinnerMerge() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.spinnerMerge)).perform(ViewActions.click());
    }

    @Test
    public void checkStartBubble()
    {
        onView(withId(R.id.startButtonBubble)).perform(ViewActions.click());
    }

    @Test
    public void checkStartInsertion() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.startButtonInsertion)).perform(ViewActions.click());
    }

    @Test
    public void checkStartSelection() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.startButtonSelection)).perform(ViewActions.click());
    }

    @Test
    public void checkStartMerge() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.startButtonMerge)).perform(ViewActions.click());
    }

    @Test
    public void checkStartQuick() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.startButtonQuick)).perform(ViewActions.click());
    }

    @Test
    public void checkStopBubble()
    {
        onView(withId(R.id.stopButtonBubble)).perform(ViewActions.click());
    }

    @Test
    public void checkStopInsertion() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.stopButtonInsertion)).perform(ViewActions.click());
    }

    @Test
    public void checkStopSelection() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.stopButtonSelection)).perform(ViewActions.click());
    }

    @Test
    public void checkStopMerge() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.stopButtonMerge)).perform(ViewActions.click());
    }

    @Test
    public void checkStopQuick() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.stopButtonQuick)).perform(ViewActions.click());
    }

    @Test
    public void checkRewindBubble()
    {
        onView(withId(R.id.rewindButtonBubble)).perform(ViewActions.click());
    }

    @Test
    public void checkRewindInsertion() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.rewindButtonInsertion)).perform(ViewActions.click());
    }

    @Test
    public void checkRewindSelection() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.rewindButtonSelection)).perform(ViewActions.click());
    }

    @Test
    public void checkRewindMerge() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.rewindButtonMerge)).perform(ViewActions.click());
    }

    @Test
    public void checkRewindQuick() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.rewindButtonQuick)).perform(ViewActions.click());
    }

    @Test
    public void checkProofBubble()
    {
        onView(withId(R.id.scrollProofBubble)).perform(ViewActions.swipeUp());
        onView(withId(R.id.proofButtonBubble)).perform(ViewActions.click());
    }

    @Test
    public void checkProofInsertion() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.scrollProofInsertion)).perform(ViewActions.swipeUp());
        onView(withId(R.id.proofButtonInsertion)).perform(ViewActions.click());
    }

    @Test
    public void checkProofSelection() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.scrollProofSelection)).perform(ViewActions.swipeUp());
        onView(withId(R.id.scrollProofSelection)).perform(ViewActions.swipeUp());
        onView(withId(R.id.proofButtonSelection)).perform(ViewActions.click());
    }

    @Test
    public void checkProofMerge() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.scrollProofMerge)).perform(ViewActions.swipeUp());
        onView(withId(R.id.scrollProofMerge)).perform(ViewActions.swipeUp());
        onView(withId(R.id.proofButtonMerge)).perform(ViewActions.click());
    }

    @Test
    public void checkProofQuick() throws InterruptedException
    {
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Thread.sleep(500);
        onView(withId(R.id.scrollProofQuick)).perform(ViewActions.swipeUp());
        onView(withId(R.id.proofButtonQuick)).perform(ViewActions.click());
    }

    @Test
    public void testSortingDestroyed()
    {
        ActivityScenario<SortingActivity> scenario = ActivityScenario.launch(SortingActivity.class);
        scenario.moveToState(Lifecycle.State.DESTROYED);
    }

    @Test
    public void testSortingRecreated()
    {
        ActivityScenario<SortingActivity> scenario = ActivityScenario.launch(SortingActivity.class);
        scenario.recreate();
    }
}