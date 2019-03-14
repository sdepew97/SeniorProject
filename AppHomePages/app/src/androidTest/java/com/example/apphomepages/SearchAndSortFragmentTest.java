//package com.example.apphomepages;
//
//import com.example.apphomepages.SearchAndSort.Fragments.SearchAndSortFragment;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import androidx.fragment.app.testing.FragmentScenario;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//
//@RunWith(AndroidJUnit4.class)
//public class SearchAndSortFragmentTest
//{
//    @Test
//    public void testSearchAndSortFragment()
//    {
//        FragmentScenario.launchInContainer(SearchAndSortFragment.class);
//        onView(withId(R.id.searchingButton)).perform(click());
//
//
//    }
//
//    /*
//    //Used https://stackoverflow.com/questions/29656562/espresso-ambiguousviewmatcherexception-when-trying-to-click-a-navigation-button
//    @Test
//    public void selectSearching()
//    {
//        //onData(withId(R.id.searchingButton)).inAdapterView(withId(R.id.viewPager)).perform(click());
//        onView(allOf(withId(R.id.searchingButton), isDisplayed())).perform(click());
//    }
//
//    @Test
//    public void selectSorting()
//    {
//        onView(withId(R.id.sortingButton)).perform(click());
//    }
//    */
//}