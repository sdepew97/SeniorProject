package com.example.apphomepages;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

//The FragmentAdapter controls all the fragments for the main page that we swipe through
public class FragmentAdapterMainPage extends FragmentPagerAdapter {

    public FragmentAdapterMainPage(FragmentManager manager) {
        super(manager);
    }

    @Override
    public int getCount() {
        return 4; //the number of pages there are on the main page
    }

    @Override
    public Fragment getItem(int i) {
        Fragment page = null;
        switch (i) {
            case 0:
                page = SearchAndSortFragment.newInstance("One", "Two");
                break;
            case 1:
                page = GraphFragment.newInstance("One", "Two");
                break; //input is number of columns of data
            case 2:
                page = GreedyFragment.newInstance("One", "Two");
                break;
            case 3:
                page = DynamicProgrammingFragment.newInstance("One", "Two");
                break;
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
                result = "Searching & Sorting";
                break;
            case 1:
                result = "Graph";
                break;
            case 2:
                result = "Greedy";
                break;
            case 3:
                result = "Dynamic Programming";
                break;
            default:
                result = "Searching & Sorting";
        }

        return result;
    }
}


