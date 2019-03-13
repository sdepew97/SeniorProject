//
//
//
///*
// * Copyright 2015, The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//
///*
//import android.app.Activity;
//import android.app.Instrumentation;
//import android.content.Context;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.support.v4.widget.DrawerLayout;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//@RunWith(AndroidJUnit4.class)
//public class MainActivityTest
//{
//    @Test
//    public void launchMain() {
//
//    }
//}
//*/
//
//package com.example.apphomepages;
//
///*
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import android.support.test.InstrumentationRegistry;
//import android.support.test.filters.LargeTest;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.support.v4.widget.DrawerLayout;
//import android.view.Gravity;
//
//import com.example.apphomepages.Main.Activities.MainActivity;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.contrib.DrawerActions.open;
//import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
//import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
//import static android.support.test.espresso.contrib.NavigationViewActions.navigateTo;
//import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.junit.Assert.fail;
//
//
//
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class MainActivityTest
//{
//    @Rule
//    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
//
//    @Test
//    public void clickOnStatisticsNavigationItem_ShowsStatisticsScreen()
//    {
//        // Open Drawer to click on navigation.
//        onView(withId(R.id.drawer_layout))
//                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
//                .perform(open()); // Open Drawer
//
//        // Start statistics screen.
//        onView(withId(R.id.nav_view))
//                .perform(navigateTo(R.id.statistics_navigation_menu_item));
//
//        // Check that statistics Activity was opened.
//        String expectedNoStatisticsText = InstrumentationRegistry.getTargetContext()
//                .getString(R.string.no_statistics_available);
//        onView(withId(R.id.no_statistics)).check(matches(withText(expectedNoStatisticsText)));
//    }
//
//    @Test
//    public void clickOnAndroidHomeIcon_OpensNavigation()
//    {
//        fail("Implement step 9");
//        // Check that left drawer is closed at startup
//        onView(withId(R.id.drawer_layout))
//                .check(matches(isClosed(Gravity.LEFT))); // Left Drawer should be closed.
//
//        // Open Drawer
//        String navigateUpDesc = mActivityTestRule.getActivity()
//                .getString(android.support.v7.appcompat.R.string.abc_action_bar_up_description);
//        onView(withContentDescription(navigateUpDesc)).perform(click());
//
//        // Check if drawer is open
//        onView(withId(R.id.drawer_layout))
//                .check(matches(isOpen(Gravity.LEFT))); // Left drawer is open open.
//    }
//
//}
//*/
//
///*
// * Copyright 2015, The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.example.android.testing.espresso.BasicSample;
//
//import android.app.Activity;
//
//import com.example.apphomepages.Main.Activities.MainActivity;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.matcher.ViewMatchers;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import androidx.test.filters.LargeTest;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//
//
///**
// * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
// * {@link ViewActions#click} and {@link ViewActions#typeText}.
// * <p>
// * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
// */
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class ChangeTextBehaviorTest {
//
//    public static final String STRING_TO_BE_TYPED = "Espresso";
//
//    /**
//     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
//     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
//     */
//    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule
//            = new ActivityScenarioRule<>(MainActivity.class);
//
//    @Test
//    public void changeText_sameActivity() {
//        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput))
//                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
//        onView(withId(R.id.changeTextBt)).perform(click());
//
//        // Check that the text was changed.
//        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
//    }
//
//    @Test
//    public void changeText_newActivity() {
//        // Type text and then press the button.
//        onView(withId(R.id.editTextUserInput)).perform(typeText(STRING_TO_BE_TYPED),
//                closeSoftKeyboard());
//        onView(withId(R.id.activityChangeTextBtn)).perform(click());
//
//        // This view is in a different Activity, no need to tell Espresso.
//        onView(withId(R.id.show_text_view)).check(matches(withText(STRING_TO_BE_TYPED)));
//    }
//}