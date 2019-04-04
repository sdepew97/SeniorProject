package com.example.apphomepages.SearchAndSort.FragmentAdapters;

import com.example.apphomepages.SearchAndSort.Fragments.BubbleSortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.InsertionSortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.MergeSortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.QuicksortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.SelectionSortFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//The FragmentAdapter controls all the fragments for the main page that we swipe through
public class FragmentAdapterSortingPage extends FragmentPagerAdapter
{

    public FragmentAdapterSortingPage(FragmentManager manager)
    {
        super(manager);
    }

    @Override
    public int getCount()
    {
        return 5; //the number of pages there are on the main page
    }

    @Override
    public CharSequence getPageTitle(int i)
    {
        CharSequence result;

        switch (i)
        {
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
                result = "Bubble Sort";
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
                page = BubbleSortFragment.newInstance();
                break;
            case 1:
                page = InsertionSortFragment.newInstance();
                break; //input is number of columns of data
            case 2:
                page = SelectionSortFragment.newInstance();
                break; //input is number of columns of data
            case 3:
                page = MergeSortFragment.newInstance();
                break; //input is number of columns of data
            case 4:
                page = QuicksortFragment.newInstance();
                break; //input is number of columns of data
            default:
                page = BubbleSortFragment.newInstance(); //default fragment to return
                break;
        }

        return page;
    }
}



