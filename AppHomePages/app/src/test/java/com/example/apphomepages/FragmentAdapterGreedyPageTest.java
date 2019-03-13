package com.example.apphomepages;

import com.example.apphomepages.Greedy.FragmentAdapters.FragmentAdapterGreedyPage;
import com.example.apphomepages.Greedy.Fragments.DijkstrasFragment;
import com.example.apphomepages.Greedy.Fragments.IntervalSchedulingFragment;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import androidx.fragment.app.FragmentManager;

import static org.hamcrest.CoreMatchers.instanceOf;

public class FragmentAdapterGreedyPageTest
{

    @Test
    public void getCount()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterGreedyPage fragmentAdapterDPPage = new FragmentAdapterGreedyPage(fm);

        Assert.assertEquals(2, fragmentAdapterDPPage.getCount());
    }

    @Test
    public void getPageTitle()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterGreedyPage fragmentAdapterGreedyPage = new FragmentAdapterGreedyPage(fm);

        Assert.assertEquals("Interval Scheduling", fragmentAdapterGreedyPage.getPageTitle(0));
        Assert.assertEquals("Dijkstra's Algorithm", fragmentAdapterGreedyPage.getPageTitle(1));
        Assert.assertEquals("Interval Scheduling", fragmentAdapterGreedyPage.getPageTitle(22));
    }

    @Test
    public void getItem()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterGreedyPage fragmentAdapterGreedyPage = new FragmentAdapterGreedyPage(fm);

        Assert.assertThat(fragmentAdapterGreedyPage.getItem(0), instanceOf(IntervalSchedulingFragment.class));
        Assert.assertThat(fragmentAdapterGreedyPage.getItem(1), instanceOf(DijkstrasFragment.class));
        Assert.assertThat(fragmentAdapterGreedyPage.getItem(22), instanceOf(IntervalSchedulingFragment.class));
    }
}