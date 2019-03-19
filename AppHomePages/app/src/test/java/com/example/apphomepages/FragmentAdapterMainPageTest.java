package com.example.apphomepages;

import com.example.apphomepages.DynamicProgramming.Fragments.DynamicProgrammingFragment;
import com.example.apphomepages.Graph.Fragments.GraphFragment;
import com.example.apphomepages.Greedy.Fragments.GreedyFragment;
import com.example.apphomepages.Main.FragmentAdapters.FragmentAdapterMainPage;
import com.example.apphomepages.SearchAndSort.Fragments.SearchAndSortFragment;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import androidx.fragment.app.FragmentManager;

import static org.hamcrest.CoreMatchers.instanceOf;

public class FragmentAdapterMainPageTest
{

    @Test
    public void getCount()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterMainPage fragmentAdapterMainPage = new FragmentAdapterMainPage(fm);

        Assert.assertEquals(4, fragmentAdapterMainPage.getCount());
    }

    @Test
    public void getPageTitle()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterMainPage fragmentAdapterMainPage = new FragmentAdapterMainPage(fm);

        Assert.assertEquals("Searching & Sorting", fragmentAdapterMainPage.getPageTitle(0));
        Assert.assertEquals("Graph", fragmentAdapterMainPage.getPageTitle(1));
        Assert.assertEquals("Greedy", fragmentAdapterMainPage.getPageTitle(2));
        Assert.assertEquals("Dynamic Programming", fragmentAdapterMainPage.getPageTitle(3));
        Assert.assertEquals("Searching & Sorting", fragmentAdapterMainPage.getPageTitle(22));
    }

    @Test
    public void getItem()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterMainPage fragmentAdapterMainPage = new FragmentAdapterMainPage(fm);

        Assert.assertThat(fragmentAdapterMainPage.getItem(0), instanceOf(SearchAndSortFragment.class));
        Assert.assertThat(fragmentAdapterMainPage.getItem(1), instanceOf(GraphFragment.class));
        Assert.assertThat(fragmentAdapterMainPage.getItem(2), instanceOf(GreedyFragment.class));
        Assert.assertThat(fragmentAdapterMainPage.getItem(3), instanceOf(DynamicProgrammingFragment.class));
        Assert.assertThat(fragmentAdapterMainPage.getItem(22), instanceOf(SearchAndSortFragment.class));
    }
}