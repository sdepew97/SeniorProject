package com.example.apphomepages.Graph.FragmentAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.apphomepages.Graph.Fragments.BFSFragment;
import com.example.apphomepages.Graph.Fragments.DFSFragment;
import com.example.apphomepages.Graph.Fragments.TopologicalFragment;

public class FragmentAdapterGraphPage extends FragmentPagerAdapter
{
    public FragmentAdapterGraphPage(FragmentManager manager)
    {
        super(manager);
    }

    @Override
    public int getCount()
    {
        return 3; //the number of pages there are on the main page
    }

    @Override
    public CharSequence getPageTitle(int i)
    {
        CharSequence result;

        switch (i)
        {
            case 0:
                result = "Depth First Search";
                break;
            case 1:
                result = "Breadth First Search";
                break;
            case 2:
                result = "Topological Ordering";
                break;
            default:
                result = "Depth First Search";
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
                page = DFSFragment.newInstance("One", "Two");
                break;
            case 1:
                page = BFSFragment.newInstance("One", "Two");
                break; //input is number of columns of data
            case 2:
                page = TopologicalFragment.newInstance("One", "Two");
                break;
            default:
                page = DFSFragment.newInstance("One", "Two"); //default fragment to return
                break;
        }

        return page;
    }
}
