package com.example.apphomepages.FragmentAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.apphomepages.Fragments.BubbleSortFragment;
import com.example.apphomepages.Fragments.InsertionSortFragment;
import com.example.apphomepages.Fragments.MergeSortFragment;
import com.example.apphomepages.Fragments.QuicksortFragment;
import com.example.apphomepages.Fragments.SearchAndSortFragment;
import com.example.apphomepages.Fragments.SelectionSortFragment;

//The FragmentAdapter controls all the fragments for the main page that we swipe through
public class FragmentAdapterSortingPage extends FragmentPagerAdapter {

    public FragmentAdapterSortingPage(FragmentManager manager) {
        super(manager);
    }

    @Override
    public int getCount() {
        return 5; //the number of pages there are on the main page
    }

    @Override
    public Fragment getItem(int i) {
        Fragment page = null;
        switch (i) {
            case 0:
                page = BubbleSortFragment.newInstance("One", "Two");
                break;
            case 1:
                page = InsertionSortFragment.newInstance("One", "Two");
                break; //input is number of columns of data
            case 2:
                page = SelectionSortFragment.newInstance("One", "Two");
                break; //input is number of columns of data
            case 3:
                page = MergeSortFragment.newInstance("One", "Two");
                break; //input is number of columns of data
            case 4:
                page = QuicksortFragment.newInstance("One", "Two");
                break; //input is number of columns of data
            default:
                page = SearchAndSortFragment.newInstance("One", "Two"); //default fragment to return
                break;
        }

        return page;
    }

    @Override
    public CharSequence getPageTitle(int i) {
        CharSequence result;

        switch (i) {
            case 0:
                result = "Bubble Sort";
                break;
            case 1:
                result = "Insertion Sort";
                break;
            case 2:
                result = "Selection Sort";
                break;
            case 3:
                result = "Merge Sort";
                break;
            case 4:
                result = "Quick Sort";
                break;
            default:
                result = "Linear Search";
        }

        return result;
    }
}



