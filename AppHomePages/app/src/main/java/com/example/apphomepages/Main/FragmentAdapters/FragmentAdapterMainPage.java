package com.example.apphomepages.Main.FragmentAdapters;

import com.example.apphomepages.DynamicProgramming.Fragments.DynamicProgrammingFragment;
import com.example.apphomepages.Graph.Fragments.GraphFragment;
import com.example.apphomepages.Greedy.Fragments.GreedyFragment;
import com.example.apphomepages.SearchAndSort.Fragments.SearchAndSortFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//The FragmentAdapter controls all the fragments for the main page that we swipe through
public class FragmentAdapterMainPage extends FragmentPagerAdapter
{

    public FragmentAdapterMainPage(FragmentManager manager)
    {
        super(manager);
    }

    @Override
    public int getCount()
    {
        return 4; //the number of pages there are on the main page
    }

    @Override
    public CharSequence getPageTitle(int i)
    {
        CharSequence result;

        switch (i)
        {
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

    @Override
    public Fragment getItem(int i)
    {
        Fragment page = null;
        switch (i)
        {
            case 0:
                page = SearchAndSortFragment.newInstance();
                break;
            case 1:
                page = GraphFragment.newInstance();
                break; //input is number of columns of data
            case 2:
                page = GreedyFragment.newInstance();
                break;
            case 3:
                page = DynamicProgrammingFragment.newInstance();
                break;
            default:
                page = SearchAndSortFragment.newInstance(); //default fragment to return
                break;
        }

        return page;
    }
}


