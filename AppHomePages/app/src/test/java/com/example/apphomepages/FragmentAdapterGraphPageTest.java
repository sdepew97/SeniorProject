package com.example.apphomepages;

import com.example.apphomepages.Graph.FragmentAdapters.FragmentAdapterGraphPage;
import com.example.apphomepages.Graph.Fragments.BFSFragment;
import com.example.apphomepages.Graph.Fragments.DFSFragment;
import com.example.apphomepages.Graph.Fragments.TopologicalFragment;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import androidx.fragment.app.FragmentManager;

import static org.hamcrest.CoreMatchers.instanceOf;

public class FragmentAdapterGraphPageTest
{

    @Test
    public void getCount()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterGraphPage fragmentAdapterDPPage = new FragmentAdapterGraphPage(fm);

        Assert.assertEquals(3, fragmentAdapterDPPage.getCount());
    }

    @Test
    public void getPageTitle()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterGraphPage fragmentAdapterGraphPage = new FragmentAdapterGraphPage(fm);

        Assert.assertEquals("Depth First Search", fragmentAdapterGraphPage.getPageTitle(0));
        Assert.assertEquals("Breadth First Search", fragmentAdapterGraphPage.getPageTitle(1));
        Assert.assertEquals("Topological Ordering", fragmentAdapterGraphPage.getPageTitle(2));
        Assert.assertEquals("Depth First Search", fragmentAdapterGraphPage.getPageTitle(22));
    }

    @Test
    public void getItem()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterGraphPage fragmentAdapterGraphPage = new FragmentAdapterGraphPage(fm);

        Assert.assertThat(fragmentAdapterGraphPage.getItem(0), instanceOf(DFSFragment.class));
        Assert.assertThat(fragmentAdapterGraphPage.getItem(1), instanceOf(BFSFragment.class));
        Assert.assertThat(fragmentAdapterGraphPage.getItem(2), instanceOf(TopologicalFragment.class));
        Assert.assertThat(fragmentAdapterGraphPage.getItem(22), instanceOf(DFSFragment.class));
    }
}