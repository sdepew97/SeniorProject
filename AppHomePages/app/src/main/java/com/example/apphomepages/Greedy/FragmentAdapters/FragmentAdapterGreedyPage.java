package com.example.apphomepages.Greedy.FragmentAdapters;

import com.example.apphomepages.Greedy.Fragments.DijkstrasFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapterGreedyPage extends FragmentPagerAdapter
{
    public FragmentAdapterGreedyPage(FragmentManager manager)
    {
        super(manager);
    }

    @Override
    public int getCount()
    {
        return 1; //the number of pages there are on the main page
    }

    @Override
    public CharSequence getPageTitle(int i)
    {
        CharSequence result;

        switch (i)
        {
            case 0:
                result = "Dijkstra's Algorithm";
                break;
            default:
                result = "Dijkstra's Algorithm";
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
                page = DijkstrasFragment.newInstance();
                break; //input is number of columns of data
            default:
                page = DijkstrasFragment.newInstance();
                break;
        }

        return page;
    }
}