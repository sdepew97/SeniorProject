package com.example.apphomepages.SearchAndSort.FragmentAdapters;

import com.example.apphomepages.SearchAndSort.Fragments.BinarySearchFragment;
import com.example.apphomepages.SearchAndSort.Fragments.LinearSearchFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
        Fragment page;
        switch (i)
        {
            case 0:
                page = LinearSearchFragment.newInstance();
                break;
            case 1:
                page = BinarySearchFragment.newInstance();
                break; //input is number of columns of data
            default:
                page = LinearSearchFragment.newInstance(); //default fragment to return
                break;
        }

        return page;
    }
}



