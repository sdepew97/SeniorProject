package com.example.apphomepages;

import com.example.apphomepages.Greedy.FragmentAdapters.FragmentAdapterGreedyPage;
import com.example.apphomepages.Greedy.Fragments.DijkstrasFragment;

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

        Assert.assertEquals(1, fragmentAdapterDPPage.getCount());
    }

    @Test
    public void getPageTitle()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterGreedyPage fragmentAdapterGreedyPage = new FragmentAdapterGreedyPage(fm);

        Assert.assertEquals("Dijkstra's Algorithm", fragmentAdapterGreedyPage.getPageTitle(0));
        Assert.assertEquals("Dijkstra's Algorithm", fragmentAdapterGreedyPage.getPageTitle(22));
    }

    @Test
    public void getItem()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterGreedyPage fragmentAdapterGreedyPage = new FragmentAdapterGreedyPage(fm);

        Assert.assertThat(fragmentAdapterGreedyPage.getItem(0), instanceOf(DijkstrasFragment.class));
        Assert.assertThat(fragmentAdapterGreedyPage.getItem(22), instanceOf(DijkstrasFragment.class));
    }
}