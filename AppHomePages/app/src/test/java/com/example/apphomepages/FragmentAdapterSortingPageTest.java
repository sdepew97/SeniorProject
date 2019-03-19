package com.example.apphomepages;

import android.support.v4.app.FragmentManager;

import com.example.apphomepages.SearchAndSort.FragmentAdapters.FragmentAdapterSortingPage;
import com.example.apphomepages.SearchAndSort.Fragments.BubbleSortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.InsertionSortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.MergeSortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.QuicksortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.SelectionSortFragment;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.instanceOf;

public class FragmentAdapterSortingPageTest
{

    @Test
    public void getCount()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterSortingPage fragmentAdapterSortingPage = new FragmentAdapterSortingPage(fm);

        Assert.assertEquals(5, fragmentAdapterSortingPage.getCount());
    }

    @Test
    public void getPageTitle()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterSortingPage fragmentAdapterSortingPage = new FragmentAdapterSortingPage(fm);

        Assert.assertEquals("Bubble Sort", fragmentAdapterSortingPage.getPageTitle(0));
        Assert.assertEquals("Insertion Sort", fragmentAdapterSortingPage.getPageTitle(1));
        Assert.assertEquals("Selection Sort", fragmentAdapterSortingPage.getPageTitle(2));
        Assert.assertEquals("Merge Sort", fragmentAdapterSortingPage.getPageTitle(3));
        Assert.assertEquals("Quick Sort", fragmentAdapterSortingPage.getPageTitle(4));
        Assert.assertEquals("Bubble Sort", fragmentAdapterSortingPage.getPageTitle(22));
    }

    @Test
    public void getItem()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterSortingPage fragmentAdapterSortingPage = new FragmentAdapterSortingPage(fm);

        Assert.assertThat(fragmentAdapterSortingPage.getItem(0), instanceOf(BubbleSortFragment.class));
        Assert.assertThat(fragmentAdapterSortingPage.getItem(1), instanceOf(InsertionSortFragment.class));
        Assert.assertThat(fragmentAdapterSortingPage.getItem(2), instanceOf(SelectionSortFragment.class));
        Assert.assertThat(fragmentAdapterSortingPage.getItem(3), instanceOf(MergeSortFragment.class));
        Assert.assertThat(fragmentAdapterSortingPage.getItem(4), instanceOf(QuicksortFragment.class));
        Assert.assertThat(fragmentAdapterSortingPage.getItem(22), instanceOf(BubbleSortFragment.class));
    }
}