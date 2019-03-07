package com.example.apphomepages;

import android.support.v4.app.FragmentManager;

import com.example.apphomepages.SearchAndSort.FragmentAdapters.FragmentAdapterSearchingPage;
import com.example.apphomepages.SearchAndSort.Fragments.BinarySearchFragment;
import com.example.apphomepages.SearchAndSort.Fragments.LinearSearchFragment;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.instanceOf;

public class FragmentAdapterSearchingPageTest
{

    @Test
    public void getCount()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterSearchingPage fragmentAdapterSearchingPage = new FragmentAdapterSearchingPage(fm);

        Assert.assertEquals(2, fragmentAdapterSearchingPage.getCount());
    }

    @Test
    public void getPageTitle()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterSearchingPage fragmentAdapterSearchingPage = new FragmentAdapterSearchingPage(fm);

        Assert.assertEquals("Linear Search", fragmentAdapterSearchingPage.getPageTitle(0));
        Assert.assertEquals("Binary Search", fragmentAdapterSearchingPage.getPageTitle(1));
        Assert.assertEquals("Linear Search", fragmentAdapterSearchingPage.getPageTitle(22));
    }

    @Test
    public void getItem()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterSearchingPage fragmentAdapterSearchingPage = new FragmentAdapterSearchingPage(fm);

        Assert.assertThat(fragmentAdapterSearchingPage.getItem(0), instanceOf(LinearSearchFragment.class));
        Assert.assertThat(fragmentAdapterSearchingPage.getItem(1), instanceOf(BinarySearchFragment.class));
        Assert.assertThat(fragmentAdapterSearchingPage.getItem(22), instanceOf(LinearSearchFragment.class));
    }
}