package com.example.apphomepages.Greedy.FragmentAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.apphomepages.Greedy.Fragments.DijkstrasFragment;
import com.example.apphomepages.Greedy.Fragments.IntervalSchedulingFragment;

public class FragmentAdapterGreedyPage extends FragmentPagerAdapter
{
    public FragmentAdapterGreedyPage(FragmentManager manager)
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
                result = "Interval Scheduling";
                break;
            case 1:
                result = "Dijkstra's Algorithm";
                break;
            default:
                result = "Interval Scheduling";
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
                page = IntervalSchedulingFragment.newInstance("One", "Two");
                break;
            case 1:
                page = DijkstrasFragment.newInstance("One", "Two");
                break; //input is number of columns of data
            default:
                page = IntervalSchedulingFragment.newInstance("One", "Two");
                break;
        }

        return page;
    }
}