package com.example.apphomepages.SearchAndSort.FragmentAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.apphomepages.SearchAndSort.Fragments.BinarySearchFragment;
import com.example.apphomepages.SearchAndSort.Fragments.LinearSearchFragment;
import com.example.apphomepages.SearchAndSort.Fragments.SearchAndSortFragment;

//The FragmentAdapter controls all the fragments for the main page that we swipe through
public class FragmentAdapterSearchingPage extends FragmentPagerAdapter
{

    public FragmentAdapterSearchingPage(FragmentManager manager)
    {
        super(manager);
    }

    @Override
    public int getCount()
    {
        return 2; //the number of pages there are on the main page
    }

    @Override
    public CharSequence getPageTitle(int i)
    {
        CharSequence result;

        switch (i)
        {
            case 0:
                result = "Linear Search";
                break;
            case 1:
                result = "Binary Search";
                break;
            default:
                result = "Linear Search";
        }

        return result;
    }

    @Override
    public Fragment getItem(int i)
    {
        Fragment page = null;
        switch (i)
        {
            case 0:
                page = LinearSearchFragment.newInstance("One", "Two");
                break;
            case 1:
                page = BinarySearchFragment.newInstance("One", "Two");
                break; //input is number of columns of data
            default:
                page = SearchAndSortFragment.newInstance("One", "Two"); //default fragment to return
                break;
        }

        return page;
    }
}



