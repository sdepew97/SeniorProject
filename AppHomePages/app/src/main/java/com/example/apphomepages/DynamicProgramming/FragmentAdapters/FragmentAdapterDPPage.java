package com.example.apphomepages.DynamicProgramming.FragmentAdapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.apphomepages.DynamicProgramming.Fragments.MinEditFragment;
import com.example.apphomepages.DynamicProgramming.Fragments.NQueensFragment;

public class FragmentAdapterDPPage extends FragmentPagerAdapter
{
    public FragmentAdapterDPPage(FragmentManager manager)
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
                result = "N-Queens";
                break;
            case 1:
                result = "Min Edit Distance";
                break;
            default:
                result = "N-Queens";
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
                page = NQueensFragment.newInstance();
                break;
            case 1:
                page = MinEditFragment.newInstance();
                break; //input is number of columns of data
            default:
                page = NQueensFragment.newInstance(); //default fragment to return
                break;
        }

        return page;
    }
}

